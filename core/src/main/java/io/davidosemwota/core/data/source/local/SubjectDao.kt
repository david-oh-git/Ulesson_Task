package io.davidosemwota.core.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.davidosemwota.core.data.Subject
import kotlinx.coroutines.flow.Flow

/**
 * Data access object for [Subject] class.
 */
@Dao
interface SubjectDao {

    @Query("SELECT * FROM subjects ")
    fun getSubjects(): Flow<List<Subject>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(subjects: List<Subject>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(subject: Subject)

    @Query("DELETE FROM subjects")
    suspend fun deleteAll()
}
