package io.davidosemwota.core.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 *  Subject Entity class for Room DB.
 */
@Entity(tableName = "subjects", indices = [ Index(value = ["subjectId"], unique = true)])
class Subject(
    @PrimaryKey(autoGenerate = true) val subjectId: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val icon: String
) {
    override fun toString() = name
}
