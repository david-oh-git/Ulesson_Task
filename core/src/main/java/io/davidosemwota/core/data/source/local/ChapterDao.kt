package io.davidosemwota.core.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import io.davidosemwota.core.data.Chapter
import io.davidosemwota.core.data.ChapterWithLessons
import kotlinx.coroutines.flow.Flow

@Dao
interface ChapterDao {

    @Query("SELECT * FROM chapters ORDER BY subjectId")
    fun getChapters(): Flow<List<Chapter>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(subjects: List<Chapter>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(subject: Chapter)

    @Query("DELETE FROM chapters")
    suspend fun deleteAll()

    @Transaction
    @Query("SELECT * FROM chapters WHERE subjectId = :queryId")
    fun getChapterWithLessonsBySubjectId(queryId: Int): List<ChapterWithLessons>

    @Transaction
    @Query("SELECT * FROM chapters ORDER BY subjectId")
    fun getChapterWithLessons(): List<ChapterWithLessons>
}
