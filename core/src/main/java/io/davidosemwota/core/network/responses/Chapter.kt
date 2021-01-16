package io.davidosemwota.core.network.responses

/**
 * Response format for a [Chapter] from the API.
 *
 * @param id The chapter id.
 * @param name The name of the chapter.
 * @param lessons A list of [Lesson]s.
 */
data class Chapter(
    val id: Int,
    val name: String,
    val lessons: List<Lesson>
)