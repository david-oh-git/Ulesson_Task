package io.davidosemwota.core.network.responses

import com.google.common.truth.Truth.assertThat
import io.davidosemwota.core.util.getSubjects
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


internal class ResponseDataTest {

    @Test
    @DisplayName("When a responseData object is created, it should hold correct attributes")
    fun createResponseData_getCorrectAttributes() {

        // Arrange: create attributes
        val status = "successful"
        val message = "successful message"
        val subjects = getSubjects(3)

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