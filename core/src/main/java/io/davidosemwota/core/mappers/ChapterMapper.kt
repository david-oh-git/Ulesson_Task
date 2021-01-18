package io.davidosemwota.core.mappers

import io.davidosemwota.core.data.Chapter
import io.davidosemwota.core.network.responses.ResponseChapter

class ChapterMapper(
    private val subjectId: Int,
    private val subjectName: String
) : Mapper<ResponseChapter, Chapter> {

    override suspend fun transform(from: ResponseChapter): Chapter {
        return Chapter(
            subjectId = subjectId,
            name = from.name,
            id = 0,
            chapterId = from.id,
            subjectName = subjectName
        )
    }
}