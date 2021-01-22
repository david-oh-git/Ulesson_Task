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
package io.davidosemwota.core.mappers

import com.google.common.truth.Truth.assertThat
import io.davidosemwota.core.network.responses.ResponseLesson
import io.davidosemwota.core.util.chapterId
import io.davidosemwota.core.util.icon
import io.davidosemwota.core.util.lessonName
import io.davidosemwota.core.util.mediaUrl
import io.davidosemwota.core.util.subjectId
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Test

internal class LessonMapperTest {

    @ExperimentalCoroutinesApi
    @Test
    fun `Transform ResponseLesson to Lesson, confirm accurate attributes`() = runBlockingTest {
        val lessonMapper = LessonMapper()
        val responseLesson = ResponseLesson(
            id = 45,
            mediaUrl = mediaUrl,
            subjectId = subjectId,
            chapterId = chapterId,
            icon = icon,
            name = lessonName
        )

        // Act transform responseLesson to lesson
        val lesson = lessonMapper.transform(responseLesson)

        // Assert: confirm attributes
        assertThat(responseLesson.chapterId).isEqualTo(lesson.chapterId)
        assertThat(responseLesson.icon).isEqualTo(lesson.icon)
        assertThat(responseLesson.mediaUrl).isEqualTo(lesson.mediaUrl)
        assertThat(responseLesson.name).isEqualTo(lesson.name)
        assertThat(responseLesson.id).isEqualTo(lesson.id)
        assertThat(lesson.lessonId).isEqualTo(0)
    }
}
