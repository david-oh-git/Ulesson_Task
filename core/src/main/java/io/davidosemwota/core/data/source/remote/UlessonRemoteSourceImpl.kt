/*
 * MIT License
 *
 * Copyright (c) 2021   David Osemwota.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package io.davidosemwota.core.data.source.remote

import io.davidosemwota.core.data.UlessonRemoteSource
import io.davidosemwota.core.network.Result
import io.davidosemwota.core.network.UlessonService
import io.davidosemwota.core.network.responses.ResponseData
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import timber.log.Timber

class UlessonRemoteSourceImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val ulessonService: UlessonService
) : UlessonRemoteSource {

    override suspend fun getLatestDateFromApi(): ResponseData? = withContext(ioDispatcher) {
        val apiResponse = safeApiCall(
            call = { ulessonService.getAllSubjects() },
            errorMsg = "Error fetching data ..."
        )

        return@withContext apiResponse?.data
    }

    private suspend fun <T : Any> safeApiCall(
        call: suspend () -> Response<T>,
        errorMsg: String
    ): T? {

        val result: Result<T> = safeApiResult(call, errorMsg)
        var data: T? = null

        when (result) {
            is Result.Success -> data = result.data
            is Result.Error -> {
                Timber.d("Error !!! : $errorMsg")
            }
        }

        return data
    }

    private suspend fun <T : Any> safeApiResult(
        call: suspend () -> Response<T>,
        errorMsg: String
    ): Result<T> {

        try {
            val response = call.invoke()
            if (response.isSuccessful) return Result.Success(response.body()!!)

            return Result.Error(
                Exception("Error occurred while getting safe api result, ERROR - $errorMsg")
            )
        } catch (e: Exception) {
            return Result.Error(
                Exception("Error occurred while getting safe api result, ERROR - $errorMsg")
            )
        }
    }
}
