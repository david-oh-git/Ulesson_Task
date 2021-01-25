/*
 * MIT License
 *
 * Copyright (c) 2021   David Osemwota.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package io.davidosemwota.chapter_list.lesson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import io.davidosemwota.chapter_list.databinding.FragmentLessonBinding
import io.davidosemwota.chapter_list.lesson.di.inject
import io.davidosemwota.core.data.Chapter
import io.davidosemwota.core.data.Lesson
import io.davidosemwota.core.data.Subject
import io.davidosemwota.ui.extentions.observe
import javax.inject.Inject

class LessonFragment : Fragment() {

    private lateinit var binding: FragmentLessonBinding
    val args: LessonFragmentArgs by navArgs()
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val viewModel: LessonViewModel by viewModels {
        viewModelFactory
    }
    private var player: SimpleExoPlayer? = null

    private var playerListener: Player.EventListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inject(this)

        playerListener = object : Player.EventListener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                super.onIsPlayingChanged(isPlaying)

                if (isPlaying) {
                    onSaveRecentLessonEvent()
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLessonBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews(args)
        observe(viewModel.lesson, ::onLessonChange)
        observe(viewModel.subject, ::onSubjectChange)
        observe(viewModel.chapter, ::onChapterDataChange)
    }

    override fun onStart() {
        super.onStart()
        initialisePlayer()
    }

    override fun onPause() {
        super.onPause()

        player?.pause()
    }

    override fun onDestroy() {
        super.onDestroy()

        player?.release()
        playerListener = null
        player = null
    }

    private fun setupViews(args: LessonFragmentArgs) {
        viewModel.getLesson(args.lessonId)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun onLessonChange(data: Lesson) {
        binding.lessonName.text = data.name
        viewModel.getSubject(data.subjectId)

        val mediaItem = MediaItem.fromUri(data.mediaUrl)
        player?.setMediaItem(mediaItem)
        player?.prepare()
    }

    private fun onChapterDataChange(chapter: Chapter) {
        binding.chapterName.text = chapter.name
        viewModel.getSubject(chapter.subjectId)
    }

    private fun onSubjectChange(subject: Subject) {
        viewModel.getChapter(subject.subjectId)
    }

    private fun initialisePlayer() {
        player = SimpleExoPlayer.Builder(requireContext()).build()
        playerListener?.let { player?.addListener(it) }
        binding.videoStreamer.player = player
    }

    fun onSaveRecentLessonEvent() {
        viewModel.saveRecentLesson(
            lessonId = args.lessonId
        )
    }

    private fun hideSystemUi() {
    }
}
