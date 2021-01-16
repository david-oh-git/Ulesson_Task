package io.davidosemwota.core.network.responses

/**
 * Generic network from the fixer API.
 *
 * @param status The status of the response. eg success
 * @param message The message from API.
 * @param subjects A list of [Subject]s from the API.
 */
data class ResponseData(
        val status: String?,
        val message: String?,
        val subjects: List<Subject>?
)