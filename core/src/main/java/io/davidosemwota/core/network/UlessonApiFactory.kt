package io.davidosemwota.core.network

import io.davidosemwota.core.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Helper to create Ulesson instance of [Retrofit].
 */
object UlessonApiFactory {

    private const val BASE_URL = "https://jackiechanbruteforce.ulesson.com/"

    /**
     *  Provider method for [HttpLoggingInterceptor] for HTTP client
     *
     *  @return Instance of http interceptor
     */
    private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    /**
     *  Provides instance of [OkHttpClient]
     *
     *  @return Instance of http client
     */
    private fun provideHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            clientBuilder.addInterceptor(interceptor)
        }
        return clientBuilder.build()
    }

    /**
     * Provider method for [Retrofit]
     *
     * @return Instance of retrofit
     */
    private fun provideRetrofitBuilder(): Retrofit
        = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client( provideHttpClient( provideHttpLoggingInterceptor()))
        .build()

    /**
     * Creates an instance of Ulesson [Retrofit] service.
     */
    fun provideUlessonApiService(): UlessonService =
        provideRetrofitBuilder().create(UlessonService::class.java)

}