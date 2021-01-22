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

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.davidosemwota.core.data.Chapter
import io.davidosemwota.core.data.Lesson
import io.davidosemwota.core.data.RecentLesson
import io.davidosemwota.core.data.Subject
import io.davidosemwota.core.data.UlessonRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LessonViewModel @Inject constructor(
    private val repository: UlessonRepository
) : ViewModel() {

    private val _chapter = MutableLiveData<Chapter>()
    val chapter = _chapter
    private val _lesson = MutableLiveData<Lesson>()
    val lesson: LiveData<Lesson> = _lesson
    private val _subject = MutableLiveData<Subject>()
    val subject: LiveData<Subject> = _subject

    fun getLesson(lessonId: Int) = viewModelScope.launch(Dispatchers.IO) {
        _lesson.postValue(
            repository.getLesson(lessonId)
        )
    }

    fun getSubject(subjectId: Int) = viewModelScope.launch(Dispatchers.IO) {
        _subject.postValue(
            repository.getSubject(subjectId)
        )
    }

    fun getChapter(subjectId: Int) = viewModelScope.launch(Dispatchers.IO) {
        val chapterId = lesson.value?.chapterId
        if (chapterId != null) {
            _chapter.postValue(
                repository.getChapter(chapterId, subjectId)
            )
        }
    }

    fun saveRecentLesson(
        lessonId: Int
    ) = viewModelScope.launch(Dispatchers.IO) {
        val lessonName = lesson.value?.name
        val mediaUrl = lesson.value?.mediaUrl
        val subjectName = _subject.value?.name
        val chapterName = chapter.value?.name

        if (lessonName != null && mediaUrl != null && subjectName != null &&
            chapterName != null
        ) {
            repository.saveRecentLesson(
                RecentLesson(
                    id = 0,
                    subjectName = subjectName,
                    chapterName = chapterName,
                    lessonId = lessonId,
                    lessonName = lessonName,
                    mediaUrl = mediaUrl
                )
            )
        }
    }
}
