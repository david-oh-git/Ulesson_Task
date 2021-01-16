package io.davidosemwota.core.network.responses

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import io.davidosemwota.core.network.UlessonService
import io.davidosemwota.core.utils.getJson
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.Rule
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

internal class UlessonServiceTest {

    object MockResponses {
        const val list_of_subject_200 = "mock-responses/get-subjects-status200.json"
    }

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var service: UlessonService
    private lateinit var mockWebServer: MockWebServer

    @BeforeEach
    fun initialise() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UlessonService::class.java)
    }

    @AfterEach
    fun reset() {
        mockWebServer.shutdown()
    }

    @Test
    fun requestGetSubjects() = runBlocking {
        val expectedRequestUrl = "/3p/api/content/grade/"
        val HTTP_REQUEST = "GET"
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return  MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody( getJson(MockResponses.list_of_subject_200) )
            }
        }

        service.getAllSubjects()
        val request = mockWebServer.takeRequest()
        assertThat(request.path).isEqualTo(expectedRequestUrl)
        assertThat(HTTP_REQUEST).isEqualTo(request.method)
    }
}