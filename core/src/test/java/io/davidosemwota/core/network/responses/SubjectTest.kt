package io.davidosemwota.core.network.responses

import com.google.common.truth.Truth.assertThat
import io.davidosemwota.core.util.getChapters
import io.davidosemwota.core.util.icon
import io.davidosemwota.core.util.id
import io.davidosemwota.core.util.lessonName
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class SubjectTest {

    @Test
    @DisplayName("When a chapter object is created, it should hold correct attributes")
    fun createSubject_getCorrectAttributes() {
        // Arrange: create attributes
        val id = id
        val name = lessonName
        val icon = icon
        val numberOfChapters = 3
        val chapters = getChapters(numberOfChapters)

        // Act: create subject object
        val subject = Subject(
            id, name, icon, chapters
        )

        // Assert: Attributes values are accurate
        assertThat(id).isEqualTo(subject.id)
        assertThat(name).isEqualTo(subject.name)
        assertThat(chapters.size).isEqualTo(subject.chapters.size)
    }
}