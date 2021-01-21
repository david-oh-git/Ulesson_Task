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
package io.davidosemwota.core.data

import kotlinx.coroutines.flow.Flow

interface UlessonLocalSource {

    suspend fun saveAllSubjects(subjects: List<Subject>)

    suspend fun saveAllChapters(chapters: List<Chapter>)

    suspend fun saveLessons(lessons: List<Lesson>)

    suspend fun saveChapter(chapter: Chapter)

    suspend fun saveLesson(lesson: Lesson)

    suspend fun deleteAllSubjects()

    suspend fun deleteAllChapters()

    suspend fun deleteAllLessons()

    fun getSubjects(): Flow<List<Subject>>

    fun getChapterWithLessonsBySubjectId(subjectId: Int): List<ChapterWithLessons>

    fun getLesson(lessonId: Int): Lesson?

    suspend fun saveRecentLesson(recentLesson: RecentLesson)

    fun getAllRecentLessons(): Flow<List<RecentLesson>>
}
