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

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import io.davidosemwota.core.data.UlessonRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel @Inject constructor(
    private val repository: UlessonRepository
) : ViewModel() {

    val subjects = liveData {
        repository.getSubjects()
            .collect { emit(it) }
    }

    val networkState = liveData {
        repository.networkStateFlow
            .collect { emit(it) }
    }

//    val state = Transformations.map(networkState) {
//
//        when (it) {
//            is NetworkState.Success -> {
//                if (it.isEmptyResponse) {
//                    HomeViewState.Empty
//                } else {
//                    HomeViewState.Loaded
//                }
//            }
//            is NetworkState.Error -> {
//                HomeViewState.Error
//            }
//            is NetworkState.Loading -> {
//                HomeViewState.Loading
//            }
//        }
//    }

    val state = MutableLiveData(HomeViewState.Loaded)

    fun refreshDataFromRemoteSource() = viewModelScope.launch {
        repository.updateDataFromApi()
    }
}
