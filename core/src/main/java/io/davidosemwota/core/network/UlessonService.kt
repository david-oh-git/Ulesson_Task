package io.davidosemwota.core.network

import io.davidosemwota.core.network.responses.UlessonApiResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * Ulesson retrofit service
 */
interface UlessonService {

    /**
     * Get a list of all subjects from Ulesson API.
     */
    @GET(SUBJECTS_ENDPOINT)
    suspend fun getAllSubjects(): Response<UlessonApiResponse>

    companion object {
        private const val SUBJECTS_ENDPOINT = "3p/api/content/grade/"
    }
}