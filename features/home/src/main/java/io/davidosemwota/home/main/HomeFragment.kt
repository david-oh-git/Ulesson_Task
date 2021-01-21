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
import io.davidosemwota.core.data.Subject
import io.davidosemwota.home.R
import io.davidosemwota.home.databinding.FragmentHomeBinding
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
        observe(viewModel.subjects, ::onViewDataChange)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        binding.includeHomeLoaded.subjectsList
            .apply {
                this.adapter = subjectAdaptor
                layoutManager = GridLayoutManager(requireContext(), 2)
                setItemDecorationSpacing(
                    resources.getDimension(R.dimen.view_subject_list_item_padding)
                )
            }
    }

    private fun onViewStateChange(viewState: HomeViewState) {

        binding.swipeRefreshLayout.isRefreshing = viewState.isRefreshing()
        when (viewState) {
            HomeViewState.Empty -> {
                binding.includeHomeEmpty.root.visible = true
                binding.includeHomeLoading.root.visible = false
                binding.includeHomeLoaded.root.visible = false
                binding.includeHomeError.root.visible = false
            }
            HomeViewState.Error -> {
                binding.includeHomeError.root.visible = true
                binding.includeHomeEmpty.root.visible = false
                binding.includeHomeLoading.root.visible = false
                binding.includeHomeLoaded.root.visible = false
            }
            HomeViewState.Loaded -> {
                binding.includeHomeLoaded.root.visible = true
                binding.includeHomeError.root.visible = false
                binding.includeHomeEmpty.root.visible = false
                binding.includeHomeLoading.root.visible = false
            }
            HomeViewState.Loading -> {
                binding.includeHomeLoading.root.visible = true
                binding.includeHomeEmpty.root.visible = false
                binding.includeHomeLoaded.root.visible = false
                binding.includeHomeError.root.visible = false
            }
            HomeViewState.Refreshing -> {
            }
        }
    }

    private fun onViewDataChange(subjects: List<Subject>) {
        subjectAdaptor.submitList(subjects)
    }

    private fun navigateToChapterListFragment(subjectName: String, subjectId: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToChapterListFragment(
            subjectId = subjectId,
            subjectName = subjectName
        )

        findNavController().navigate(action)
    }
}
