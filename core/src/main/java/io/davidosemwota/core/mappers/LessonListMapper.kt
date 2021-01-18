package io.davidosemwota.core.mappers

import io.davidosemwota.core.data.Lesson
import io.davidosemwota.core.network.responses.ResponseChapter
import javax.inject.Inject

class LessonListMapper @Inject constructor(
    private val lessonMapper: LessonMapper
) : Mapper<ResponseChapter, List<Lesson>> {

    override suspend fun transform(from: ResponseChapter): List<Lesson> {
        return from.lessons.map { lessonMapper.transform(it) }
    }
}