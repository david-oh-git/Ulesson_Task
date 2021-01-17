package io.davidosemwota.core.data

import androidx.room.Embedded
import androidx.room.Relation

data class ChapterWithLessons(
    @Embedded val chapter: Chapter,
    @Relation(
        parentColumn = "chapterId",
        entityColumn = "chapter_id"
    )
    val lessons: List<Lesson>
)
