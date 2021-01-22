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
package io.davidosemwota.home.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import io.davidosemwota.core.data.RecentLesson
import io.davidosemwota.core.data.Subject
import io.davidosemwota.home.R
import io.davidosemwota.home.databinding.FragmentHomeBinding
import io.davidosemwota.home.main.adaptor.RecentLessonAdaptor
import io.davidosemwota.home.main.adaptor.SubjectAdaptor
import io.davidosemwota.home.main.di.inject
import io.davidosemwota.ui.extentions.observe
import io.davidosemwota.ui.extentions.setItemDecorationSpacing
import io.davidosemwota.ui.extentions.visible
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: HomeViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: FragmentHomeBinding

    private val subjectAdaptor by lazy {
        SubjectAdaptor(::navigateToChapterListFragment)
    }

    private val recentLessonAdaptor by lazy {
        RecentLessonAdaptor(::navigateToLessonFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inject(this)
        viewModel.refreshDataFromRemoteSource()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(viewModel.state, ::onViewStateChange)
        observe(viewModel.subjects, ::onSubjectListDataChange)
        observe(viewModel.recentLessons, ::onRecentLessonDataChange)
        observe(viewModel.isCacheAvailable, ::onCacheDataIsAvailable)
        setUpViews()
    }

    private fun setUpViews() {
        binding.includeHomeLoaded.subjectsList
            .apply {
                adapter = subjectAdaptor
                layoutManager = GridLayoutManager(requireContext(), 2)
                setItemDecorationSpacing(
                    resources.getDimension(R.dimen.view_subject_list_item_padding)
                )
            }

        binding.includeHomeLoaded.recentlyWatchedList
            .apply {
                adapter = recentLessonAdaptor
                setItemDecorationSpacing(
                    resources.getDimension(R.dimen.view_subject_list_item_padding)
                )
            }

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshDataFromRemoteSource()
        }

        binding.collapseButton.apply {
            text = getString(R.string.home_see_less_btn)
            setOnClickListener {
                recentLessonAdaptor.collapseList()
                if (recentLessonAdaptor.colapse) {
                    this.text = getString(R.string.home_view_all_btn)
                } else
                    this.text = getString(R.string.home_see_less_btn)
            }
        }
    }

    private fun onViewStateChange(viewState: HomeViewState) {

        when (viewState) {
            HomeViewState.Empty -> {
                binding.includeHomeEmpty.root.visible = true
                binding.includeHomeLoading.root.visible = false
                binding.includeHomeLoaded.root.visible = false
                binding.includeHomeError.root.visible = false

                binding.swipeRefreshLayout.isRefreshing = false
            }
            HomeViewState.Error -> {
                errorViewState()
            }
            HomeViewState.Loaded -> {
                loadedViewState()
            }
            HomeViewState.Loading -> {
                binding.includeHomeLoading.root.visible = true
                binding.includeHomeEmpty.root.visible = false
                binding.includeHomeLoaded.root.visible = false
                binding.includeHomeError.root.visible = false
                binding.collapseButton.visible = false

                binding.swipeRefreshLayout.isRefreshing = true
            }
            HomeViewState.Refreshing -> {
            }
        }
    }

    private fun onSubjectListDataChange(subjects: List<Subject>) {
        subjectAdaptor.submitList(subjects)
    }

    private fun onRecentLessonDataChange(recentLessons: List<RecentLesson>) {
        recentLessonAdaptor.submitList(recentLessons.reversed())
        binding.collapseButton.visible = recentLessons.size > 2
    }

    private fun navigateToChapterListFragment(subjectName: String, subjectId: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToChapterListFragment(
            subjectId = subjectId,
            subjectName = subjectName
        )

        findNavController().navigate(action)
    }

    private fun navigateToLessonFragment(chapterName: String, lessonId: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToLessonFragment(
            lessonId = lessonId,
            chapterName = chapterName
        )
        findNavController().navigate(action)
    }

    private fun loadedViewState() {
        binding.includeHomeError.root.visible = false
        binding.includeHomeEmpty.root.visible = false
        binding.includeHomeLoading.root.visible = false
        binding.includeHomeLoaded.root.visible = true

        binding.swipeRefreshLayout.isRefreshing = false
    }

    private fun errorViewState() {
        binding.includeHomeError.root.visible = true
        binding.includeHomeEmpty.root.visible = false
        binding.includeHomeLoading.root.visible = false
        binding.includeHomeLoaded.root.visible = false

        binding.swipeRefreshLayout.isRefreshing = false
    }

    private fun onCacheDataIsAvailable(isCacheAvailable: Boolean) {
        if (isCacheAvailable) {
            loadedViewState()
        }
    }
}
