package io.davidosemwota.core.data

import io.davidosemwota.core.network.NetworkState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

interface UlessonRepository {

    val networkStateFlow: MutableStateFlow<NetworkState>

    suspend fun saveAllSubjects(subjects: List<Subject>)

    suspend fun saveChapter(chapter: Chapter)

    suspend fun saveLesson(lesson: Lesson)

    fun getSubjects(): Flow<List<Subject>>

    suspend fun updateDataFromApi()
}