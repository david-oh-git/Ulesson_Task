package io.davidosemwota.core.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "lessons"
)
data class Lesson(
    @PrimaryKey val lessonId: Int,
    @ColumnInfo val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val icon: String,
    @ColumnInfo val mediaUrl: String,
    @ColumnInfo(name = "subject_id") val subjectId: Int,
    @ColumnInfo(name = "chapter_id") val chapterId: Int
)
