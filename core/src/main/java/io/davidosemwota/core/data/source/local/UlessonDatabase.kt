package io.davidosemwota.core.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import io.davidosemwota.core.data.Chapter
import io.davidosemwota.core.data.Lesson
import io.davidosemwota.core.data.Subject

@Database(
    entities = [Subject::class, Chapter::class, Lesson::class],
    version = 1,
    exportSchema = false
)
abstract class UlessonDatabase : RoomDatabase() {

    abstract fun subjectDao(): SubjectDao

    abstract fun chapterDao(): ChapterDao

    abstract fun lessonDao(): LessonDao
}
