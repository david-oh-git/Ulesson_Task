package io.davidosemwota.core.network.responses

/**
 * Response format for a subject from the API.
 *
 * @param id The id of the subject.
 * @param name The subject name.
 * @param icon The url for the subject's icon.
 * @param chapters A list of [Chapter]s.
 */
data class Subject(
    val id: Int?,
    val name: String?,
    val icon: String?,
    val chapters: List<Chapter>
)