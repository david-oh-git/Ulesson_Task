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
        val lesson =  Lesson(
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