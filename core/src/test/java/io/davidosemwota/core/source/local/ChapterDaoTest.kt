/*
 * MIT License
 *
 * Copyright (c) $today.day/$today.month/2021 $today.hour24:$today.minute   David Osemwota.
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
import io.davidosemwota.core.data.source.local.ChapterDao
import io.davidosemwota.core.data.source.local.LessonDao
import io.davidosemwota.core.data.source.local.UlessonDatabase
import io.davidosemwota.core.util.getChapter
import io.davidosemwota.core.util.getChapters
import io.davidosemwota.core.util.getLesson
import io.davidosemwota.core.util.rules.MainCoroutineRule
import io.davidosemwota.core.util.subjectId
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
internal class ChapterDaoTest {

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var database: UlessonDatabase
    private lateinit var chapterDao: ChapterDao
    private lateinit var lessonDao: LessonDao

    @Before
    fun initialise() {
        val applicationContext = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            applicationContext,
            UlessonDatabase::class.java
        ).allowMainThreadQueries()
            .build()

        chapterDao = database.chapterDao()
        lessonDao = database.lessonDao()
    }

    @After
    fun reset() {
        database.close()
    }

    @Test
    fun `save a chapter then confirm the chapter is saved`() = runBlockingTest {
        // Arrange: save a chapter.
        val expectedChapter = getChapter()
        chapterDao.insert(expectedChapter)

        // Act: Get all chapters from the database.
        val chapter = chapterDao.getChapters().take(1).toList()[0][0]
        // Assert: confirm results
        assertThat(expectedChapter.chapterId).isEqualTo(chapter.chapterId)
        assertThat(expectedChapter.name).isEqualTo(chapter.name)
        assertThat(expectedChapter.subjectName).isEqualTo(chapter.subjectName)
        assertThat(expectedChapter.subjectId).isEqualTo(chapter.subjectId)
    }

    @Test
    fun `save a list of chapters, delete them confirm they are deleted`() = runBlockingTest {
        // Arrange: save a list of chapters.
        chapterDao.insertAll(getChapters(6))

        // Act: Get all chapters from the database.
        chapterDao.deleteAll()

        // Assert: confirm results
        val chapters = chapterDao.getChapters().take(1).toList()[0]
        assertThat(chapters.isEmpty()).isTrue()
    }

    @Test
    fun `save chaptersWithLessons, confirm saved chapterWithLessons`() = runBlockingTest {
        // Arrange: save a chapter  and its lessons.
        val subjectId = subjectId
        val expectedChapter = getChapter().copy(subjectId = subjectId)
        val expectedLessonOne = getLesson().copy(
            chapterId = expectedChapter.chapterId,
            subjectId = subjectId
        )
        val expectedLessonTwo = getLesson().copy(
            chapterId = expectedChapter.chapterId,
            subjectId = subjectId
        )
        lessonDao.insertAll(listOf(expectedLessonOne, expectedLessonTwo))
        chapterDao.insert(expectedChapter)

        // Act; get all chapterWithLessons
        val result = chapterDao.getChapterWithLessonsBySubjectId(subjectId)
        assertThat(result.isEmpty()).isFalse()
        val chapterWithLessons = result[0]
        assertThat(chapterWithLessons.chapter).isEqualTo(expectedChapter)
    }
}
