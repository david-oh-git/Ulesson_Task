/*
 * MIT License
 *
 * Copyright (c) $today.day/$today.month/2021 $today.hour24:$today.minute   David Osemwota.
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
package io.davidosemwota.chapter_list.chapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import io.davidosemwota.chapter_list.R
import io.davidosemwota.chapter_list.chapters.adaptors.ChapterAdaptor
import io.davidosemwota.chapter_list.chapters.di.inject
import io.davidosemwota.chapter_list.databinding.FragmentChapterListBinding
import io.davidosemwota.core.data.ChapterWithLessons
import io.davidosemwota.ui.extentions.observe
import io.davidosemwota.ui.extentions.setItemDecorationSpacing
import io.davidosemwota.ui.extentions.visible
import javax.inject.Inject
import timber.log.Timber

class ChapterListFragment : Fragment() {

    private lateinit var binding: FragmentChapterListBinding
    private val args: ChapterListFragmentArgs by navArgs()
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: ChapterListViewModel by viewModels {
        viewModelFactory
    }

    private val chapterAdaptor by lazy {
        ChapterAdaptor()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentChapterListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (args.subjectId != -1)
            viewModel.getChapters(args.subjectId)

        observe(viewModel.chapterWithLessons, ::onDataChange)
        observe(viewModel.state, ::onViewStateChange)
        setToolBarTitle()
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        binding.includeChapterListLoaded.chaptersList.apply {
            this.adapter = chapterAdaptor
            setItemDecorationSpacing(
                resources.getDimension(R.dimen.view_chapter_list_item_padding)
            )
        }
    }

    private fun setToolBarTitle() {

        binding.toolbar.apply {
            title = args.subjectName
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun onViewStateChange(viewState: ChapterListViewState) {

        when (viewState) {
            ChapterListViewState.Empty -> {
                Timber.d("State is Empty")
                binding.includeChapterListEmpty.root.visible = true
                binding.includeChapterListLoaded.root.visible = false
                binding.includeChapterListLoading.root.visible = false
                binding.includeChapterListError.root.visible = false
            }
            ChapterListViewState.Loaded -> {
                Timber.d("State is Loaded")
                binding.includeChapterListLoaded.root.visible = true
                binding.includeChapterListEmpty.root.visible = false
                binding.includeChapterListLoading.root.visible = false
                binding.includeChapterListError.root.visible = false
            }
            ChapterListViewState.Loading -> {
                binding.includeChapterListLoading.root.visible = true
                binding.includeChapterListEmpty.root.visible = false
                binding.includeChapterListLoaded.root.visible = false
                binding.includeChapterListError.root.visible = false
            }
            ChapterListViewState.Error -> {
                binding.includeChapterListError.root.visible = true
                binding.includeChapterListLoading.root.visible = false
                binding.includeChapterListEmpty.root.visible = false
                binding.includeChapterListLoaded.root.visible = false
            }
        }
    }

    private fun onDataChange(data: List<ChapterWithLessons>) {
        chapterAdaptor.submitList(data)
    }
}
