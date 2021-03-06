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
import io.davidosemwota.core.util.getResponseSubjects
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class ResponseDataTest {

    @Test
    @DisplayName("When a responseData object is created, it should hold correct attributes")
    fun createResponseData_getCorrectAttributes() {

        // Arrange: create attributes
        val status = "successful"
        val message = "successful message"
        val subjects = getResponseSubjects(3)

        // Act: create responseData object
        val response = ResponseData(
            status = status,
            message = message,
            subjects = subjects
        )

        // Assert: Attributes values are accurate
        assertThat(status).isEqualTo(response.status)
        assertThat(message).isEqualTo(response.message)
        assertThat(response.subjects.isNullOrEmpty()).isFalse()
        assertThat(subjects.size).isEqualTo(subjects.size)
    }
}
