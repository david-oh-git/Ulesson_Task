package io.davidosemwota.core.source.local

import android.content.Context
import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth
import io.davidosemwota.core.data.source.local.SubjectDao
import io.davidosemwota.core.data.source.local.UlessonDatabase
import io.davidosemwota.core.util.getSubject
import io.davidosemwota.core.util.getSubjects
import io.davidosemwota.core.util.rules.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@ExperimentalCoroutinesApi
@Config(sdk = [Build.VERSION_CODES.O])
internal class SubjectDaoTest {

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()
    private lateinit var database: UlessonDatabase
    private lateinit var subjectDao: SubjectDao

    @Before
    fun initialise() {
        val applicationContext = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            applicationContext,
            UlessonDatabase::class.java
        ).allowMainThreadQueries()
            .build()

        subjectDao = database.subjectDao()
    }

    @After
    fun reset() {
        database.close()
    }

    @Test
    fun `save a subject then confirm it is saved`() = runBlockingTest {
        // Arrange: save a subject.
        val expectedSubject = getSubject()
        subjectDao.insert(expectedSubject)

        // Act: Get all chapters from the database.
        val subject = subjectDao.getSubjects().take(1).toList()[0][0]
        // Assert: confirm results
        Truth.assertThat(expectedSubject.subjectId).isEqualTo(subject.subjectId)
        Truth.assertThat(expectedSubject.name).isEqualTo(subject.name)
        Truth.assertThat(expectedSubject.icon).isEqualTo(subject.icon)
    }

    @Test
    fun `save a list of subjectss, delete them confirm they are deleted`() = runBlockingTest {
        // Arrange: save a list of subjects.
        subjectDao.insertAll(getSubjects(6))

        // Act: Get all subjects from the database.
        subjectDao.deleteAll()

        // Assert: confirm results
        val subjects = subjectDao.getSubjects().take(1).toList()[0]
        Truth.assertThat(subjects.isEmpty()).isTrue()
    }
}
