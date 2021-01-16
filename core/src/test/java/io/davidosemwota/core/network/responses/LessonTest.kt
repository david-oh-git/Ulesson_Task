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
package io.davidosemwota.core.network.responses

import com.google.common.truth.Truth.assertThat
import io.davidosemwota.core.util.chapterId
import io.davidosemwota.core.util.icon
import io.davidosemwota.core.util.id
import io.davidosemwota.core.util.lessonName
import io.davidosemwota.core.util.mediaUrl
import io.davidosemwota.core.util.subjectId
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class LessonTest {

    @Test
    @DisplayName("When a lesson object is created, it should hold correct attributes")
    fun createLesson_getCorrectAttributes() {
        // Arrange: create object attributes
        val id = id
        val name = lessonName
        val icon = icon
        val mediaUrl = mediaUrl
        val subjectId = subjectId
        val chapterId = chapterId

        // Act: create lesson object
        val lesson = Lesson(
            id = id,
            name = name,
            icon = icon,
            mediaUrl = mediaUrl,
            subjectId = subjectId,
            chapterId = chapterId
        )

        // Assert: Attributes values are accurate
        assertThat(id).isEqualTo(lesson.id)
        assertThat(name).isEqualTo(lesson.name)
        assertThat(icon).isEqualTo(lesson.icon)
        assertThat(mediaUrl).isEqualTo(lesson.mediaUrl)
        assertThat(subjectId).isEqualTo(lesson.subjectId)
        assertThat(chapterId).isEqualTo(lesson.chapterId)
    }
}
