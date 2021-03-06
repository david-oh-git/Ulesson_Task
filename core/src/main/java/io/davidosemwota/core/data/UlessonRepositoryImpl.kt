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
package io.davidosemwota.core.data

import io.davidosemwota.core.mappers.ChapterMapper
import io.davidosemwota.core.mappers.LessonListMapper
import io.davidosemwota.core.mappers.SubjectListMapper
import io.davidosemwota.core.network.NetworkState
import io.davidosemwota.core.network.responses.ResponseData
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext

class UlessonRepositoryImpl @Inject constructor(
    private val remoteSource: UlessonRemoteSource,
    private val localSource: UlessonLocalSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val subjectListMapper: SubjectListMapper,
    private val lessonListMapper: LessonListMapper
) : UlessonRepository {

    override val networkStateFlow = MutableStateFlow<NetworkState>(NetworkState.Loading)

    override suspend fun saveAllSubjects(subjects: List<Subject>) = withContext(ioDispatcher) {
        localSource.saveAllSubjects(subjects)
    }

    override suspend fun saveChapter(chapter: Chapter) = withContext(ioDispatcher) {
        localSource.saveChapter(chapter)
    }

    override suspend fun saveLesson(lesson: Lesson) = withContext(ioDispatcher) {
        localSource.saveLesson(lesson)
    }

    override fun getSubjects(): Flow<List<Subject>> {
        return localSource.getSubjects()
    }

    override suspend fun updateDataFromApi(): Unit = withContext(ioDispatcher) {
        networkStateFlow.value = NetworkState.Loading
        val apiResponse = remoteSource.getLatestDateFromApi()

        when {
            apiResponse == null -> {
                networkStateFlow.value = NetworkState.Error
            }
            apiResponse.subjects.isEmpty() -> {
                networkStateFlow.value = NetworkState.Success(true)
            }
            else -> {
                parseResponseData(apiResponse)
                parseResponseSubjects(apiResponse)
                networkStateFlow.value = NetworkState.Success()
            }
        }
    }

    override fun getChapterWithLessonsBySubjectId(subjectId: Int): List<ChapterWithLessons> {
        return localSource.getChapterWithLessonsBySubjectId(subjectId)
    }

    override fun getLesson(lessonId: Int): Lesson? =
        localSource.getLesson(lessonId)

    override fun getSubject(subjectId: Int): Subject? =
        localSource.getSubject(subjectId)

    override fun getChapter(chapterId: Int, subjectId: Int): Chapter? =
        localSource.getChapter(chapterId, subjectId)

    override suspend fun saveRecentLesson(recentLesson: RecentLesson) = withContext(ioDispatcher) {
        localSource.saveRecentLesson(recentLesson)
    }

    override fun getAllRecentLessons(): Flow<List<RecentLesson>> =
        localSource.getAllRecentLessons()

    private suspend fun parseResponseData(responseData: ResponseData) = withContext(ioDispatcher) {
        val subjects = subjectListMapper.transform(responseData)
        saveLatestSubjects(subjects)
    }

    private suspend fun parseResponseSubjects(
        responseData: ResponseData
    ) = withContext(ioDispatcher) {

        val allChapters = mutableListOf<Chapter>()
        val allLessons = mutableListOf<Lesson>()
        for (responseSubject in responseData.subjects) {
            val chapterMapper = ChapterMapper(responseSubject.id, responseSubject.name)
            val chapters = responseSubject.chapters.map { chapterMapper.transform(it) }
            responseSubject.chapters.map {
                allLessons.addAll(lessonListMapper.transform(it))
            }
            allChapters.addAll(chapters)
        }

        saveLatestChapters(allChapters)
        saveLatestLessons(allLessons)
    }

    private suspend fun saveLatestSubjects(subjects: List<Subject>) = withContext(ioDispatcher) {
        localSource.deleteAllSubjects()
        localSource.saveAllSubjects(subjects)
    }

    private suspend fun saveLatestChapters(chapters: List<Chapter>) = withContext(ioDispatcher) {
        localSource.deleteAllChapters()
        localSource.saveAllChapters(chapters)
    }

    private suspend fun saveLatestLessons(lessons: List<Lesson>) = withContext(ioDispatcher) {
        localSource.deleteAllLessons()
        localSource.saveLessons(lessons)
    }
}
