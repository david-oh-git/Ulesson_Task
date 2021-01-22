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

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.davidosemwota.core.data.ChapterWithLessons
import io.davidosemwota.core.data.UlessonRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChapterListViewModel @Inject constructor(
    private val repository: UlessonRepository
) : ViewModel() {

    private val _chapterWithLessons = MutableLiveData<List<ChapterWithLessons>>()
    val chapterWithLessons: LiveData<List<ChapterWithLessons>> = _chapterWithLessons

    val state = Transformations.map(chapterWithLessons) {
        when {
            it.isEmpty() -> ChapterListViewState.Empty
            it.isNotEmpty() -> ChapterListViewState.Loaded
            it == null -> ChapterListViewState.Error
            else -> ChapterListViewState.Loading
        }
    }

    fun getChapters(subjectId: Int) = viewModelScope.launch(Dispatchers.IO) {
        _chapterWithLessons.postValue(
            repository.getChapterWithLessonsBySubjectId(subjectId)
        )
    }
}
