package io.davidosemwota.core.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chapters")
data class Chapter(
    @PrimaryKey val id: Int,
    @ColumnInfo val chapterId: Int,
    @ColumnInfo val subjectId: Int,
    @ColumnInfo val subjectName: String,
    @ColumnInfo val name: String
)
