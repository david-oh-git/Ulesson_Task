package io.davidosemwota.core.data

import io.davidosemwota.core.data.source.local.ChapterDao
import io.davidosemwota.core.data.source.local.LessonDao
import io.davidosemwota.core.data.source.local.SubjectDao
import io.davidosemwota.core.mappers.ChapterMapper
import io.davidosemwota.core.mappers.LessonListMapper
import io.davidosemwota.core.mappers.SubjectListMapper
import io.davidosemwota.core.network.NetworkState
import io.davidosemwota.core.network.responses.ResponseData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UlessonRepositoryImpl @Inject constructor(
    private val remoteSource: UlessonRemoteSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val subjectDao: SubjectDao,
    private val chapterDao: ChapterDao,
    private val lessonDao: LessonDao,
    private val subjectListMapper: SubjectListMapper,
    private val lessonListMapper: LessonListMapper
) : UlessonRepository {

    override val networkStateFlow = MutableStateFlow<NetworkState>(NetworkState.Loading)

    override suspend fun saveAllSubjects(subjects: List<Subject>) = withContext(ioDispatcher){
        subjectDao.insertAll(subjects)
    }

    override suspend fun saveChapter(chapter: Chapter) = withContext(ioDispatcher) {
        chapterDao.insert(chapter)
    }

    override suspend fun saveLesson(lesson: Lesson) = withContext(ioDispatcher) {
        lessonDao.insert(lesson)
    }

    override fun getSubjects(): Flow<List<Subject>> {
        return subjectDao.getSubjects()
    }

    override suspend fun updateDataFromApi(): Unit = withContext(ioDispatcher) {
        networkStateFlow.value = NetworkState.Loading
        val apiResponse = remoteSource.getLatestDateFromApi()

        if (apiResponse == null ){
            networkStateFlow.value = NetworkState.Error
        }else{
            parseResponseData(apiResponse)
            parseResponseSubjects(apiResponse)
            networkStateFlow.value = NetworkState.Success()
        }
    }

    private suspend fun parseResponseData(responseData: ResponseData) = withContext(ioDispatcher) {
        val subjects = subjectListMapper.transform(responseData)
        saveLatestSubjects(subjects)
    }

    private suspend fun parseResponseSubjects(
        responseData: ResponseData
    ) = withContext(ioDispatcher) {

        val allChapters = mutableListOf<Chapter>()
        val allLessons = mutableListOf<Lesson>()
        for (responseSubject in responseData.subjects){
            val chapterMapper = ChapterMapper(responseSubject.id, responseSubject.name)
            val chapters = responseSubject.chapters.map { chapterMapper.transform(it) }
            responseSubject.chapters.map {
                allLessons.addAll( lessonListMapper.transform(it) )
            }
            allChapters.addAll(chapters)
        }

        saveLatestChapters(allChapters)
        saveLatestLessons(allLessons)
    }

    private suspend fun saveLatestSubjects(subjects: List<Subject>) = withContext(ioDispatcher) {
        subjectDao.deleteAll()
        subjectDao.insertAll(subjects)
    }

    private suspend fun saveLatestChapters(chapters: List<Chapter>) = withContext(ioDispatcher) {
        chapterDao.deleteAll()
        chapterDao.insertAll(chapters)
    }

    private suspend fun saveLatestLessons(lessons: List<Lesson>) = withContext(ioDispatcher) {
        lessonDao.deleteAll()
        lessonDao.insertAll(lessons)
    }

}