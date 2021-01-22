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
package io.davidosemwota.core.network.responses

import com.google.common.truth.Truth.assertThat
import io.davidosemwota.core.util.getResponseLessons
import io.davidosemwota.core.util.id
import io.davidosemwota.core.util.lessonName
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class ResponseChapterTest {

    @Test
    @DisplayName("When a chapter object is created, it should hold correct attributes")
    fun createChapter_getCorrectAttributes() {
        // Arrange: create attributes
        val id = id
        val name = lessonName
        val lessons = getResponseLessons(3)

        // Act: create chapter object
        val chapter = ResponseChapter(
            id, name, lessons
        )

        // Assert: Attributes values are accurate
        assertThat(id).isEqualTo(chapter.id)
        assertThat(name).isEqualTo(chapter.name)
        assertThat(lessons.isEmpty()).isEqualTo(chapter.lessons.isEmpty())
        assertThat(lessons.size).isEqualTo(chapter.lessons.size)
    }
}
