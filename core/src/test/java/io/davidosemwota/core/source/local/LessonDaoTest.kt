/*
 * MIT License
 *
 * Copyright (c) 2021   David Osemwota.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package io.davidosemwota.core.source.local

import android.content.Context
import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import io.davidosemwota.core.data.source.local.LessonDao
import io.davidosemwota.core.data.source.local.UlessonDatabase
import io.davidosemwota.core.util.getLesson
import io.davidosemwota.core.util.getLessons
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
class LessonDaoTest {

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()
    private lateinit var database: UlessonDatabase
    private lateinit var lessonDao: LessonDao

    @Before
    fun initialise() {
        val applicationContext = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            applicationContext,
            UlessonDatabase::class.java
        ).allowMainThreadQueries()
            .build()

        lessonDao = database.lessonDao()
    }

    @After
    fun reset() {
        database.close()
    }

    @Test
    fun `save a lesson then confirm it is saved`() = runBlockingTest {
        // Arrange: save a lesson.
        val expectedLesson = getLesson()
        lessonDao.insert(expectedLesson)

        // Act: Get all lessons from the database.
        val lesson = lessonDao.getLessons().take(1).toList()[0][0]
        // Assert: confirm results
        assertThat(expectedLesson.subjectId).isEqualTo(lesson.subjectId)
        assertThat(expectedLesson.name).isEqualTo(lesson.name)
        assertThat(expectedLesson.icon).isEqualTo(lesson.icon)
        assertThat(expectedLesson.chapterId).isEqualTo(lesson.chapterId)
        assertThat(expectedLesson.lessonId).isEqualTo(lesson.lessonId)
        assertThat(expectedLesson.mediaUrl).isEqualTo(lesson.mediaUrl)
    }

    @Test
    fun `save a list of subjectss, delete them confirm they are deleted`() = runBlockingTest {
        // Arrange: save a list of lessons.
        lessonDao.insertAll(getLessons(6))

        // Act: Get all subjects from the database.
        lessonDao.deleteAll()

        // Assert: confirm results
        val lessons = lessonDao.getLessons().take(1).toList()[0]
        assertThat(lessons.isEmpty()).isTrue()
    }

    @Test
    fun ` save a list of lessons then get get a lesson by lessonId`() = runBlockingTest {
        // Arrange: save a list of lessons.
        val lessonId = 999
        val lesson = getLesson().copy(lessonId = lessonId)
        lessonDao.insertAll(getLessons(6))
        lessonDao.insert(lesson)

        // Act: get lesson with lessonId
        val result = lessonDao.getLesson(lessonId)

        // Assert: confirm result
        assertThat(lesson).isEqualTo(result)
    }
}
