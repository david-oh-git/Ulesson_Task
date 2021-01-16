package io.davidosemwota.core.network.responses

import com.google.common.truth.Truth.assertThat
import io.davidosemwota.core.util.getLessons
import io.davidosemwota.core.util.id
import io.davidosemwota.core.util.lessonName
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class ChapterTest {

    @Test
    @DisplayName("When a chapter object is created, it should hold correct attributes")
    fun createChapter_getCorrectAttributes() {
        // Arrange: create attributes
        val id = id
        val name = lessonName
        val lessons = getLessons(3)

        // Act: create chapter object
        val chapter = Chapter(
            id, name, lessons
        )

        // Assert: Attributes values are accurate
        assertThat(id).isEqualTo(chapter.id)
        assertThat(name).isEqualTo(chapter.name)
        assertThat(lessons.isEmpty()).isEqualTo(chapter.lessons.isEmpty())
        assertThat(lessons.size).isEqualTo(chapter.lessons.size)
    }
}