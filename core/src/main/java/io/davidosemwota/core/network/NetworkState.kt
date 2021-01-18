package io.davidosemwota.core.network

sealed class NetworkState {

    data class Success(
        val isEmptyResponse: Boolean = false
    ) : NetworkState()

    object Loading : NetworkState()

    object Error : NetworkState()

    fun isSuccess() = this is Success

    fun isLoading() = this is Loading

    fun isError() = this is Error
}