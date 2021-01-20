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
package io.davidosemwota.core.data.source.local

import io.davidosemwota.core.data.Chapter
import io.davidosemwota.core.data.ChapterWithLessons
import io.davidosemwota.core.data.Lesson
import io.davidosemwota.core.data.Subject
import io.davidosemwota.core.data.UlessonLocalSource
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext

class UlessonLocalSourceImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val subjectDao: SubjectDao,
    private val chapterDao: ChapterDao,
    private val lessonDao: LessonDao
) : UlessonLocalSource {

    override suspend fun saveAllSubjects(subjects: List<Subject>) = withContext(ioDispatcher) {
        subjectDao.insertAll(subjects)
    }

    override suspend fun saveAllChapters(chapters: List<Chapter>) = withContext(ioDispatcher) {
        chapterDao.insertAll(chapters)
    }

    override suspend fun saveLessons(lessons: List<Lesson>) = withContext(ioDispatcher) {
        lessonDao.insertAll(lessons)
    }

    override suspend fun saveChapter(chapter: Chapter) = withContext(ioDispatcher) {
        chapterDao.insert(chapter)
    }

    override suspend fun saveLesson(lesson: Lesson) = withContext(ioDispatcher) {
        lessonDao.insert(lesson)
    }

    override suspend fun deleteAllSubjects() = withContext(ioDispatcher) {
        subjectDao.deleteAll()
    }

    override suspend fun deleteAllChapters() = withContext(ioDispatcher) {
        chapterDao.deleteAll()
    }

    override suspend fun deleteAllLessons() = withContext(ioDispatcher) {
        lessonDao.deleteAll()
    }

    override fun getSubjects(): Flow<List<Subject>> =
        subjectDao.getSubjects()

    override fun getChapterWithLessonsBySubjectId(subjectId: Int): Flow<List<ChapterWithLessons>> {
        val chapterWithLessons = chapterDao.getChapterWithLessonsBySubjectId(subjectId)
        return MutableStateFlow(chapterWithLessons)
    }
}
