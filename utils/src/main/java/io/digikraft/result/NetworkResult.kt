package io.digikraft.result


sealed class NetworkResult<out T>

data class ErrorApiResult<out T>(
    val message: String,
    val throwable: Throwable? = null
) : NetworkResult<T>()

data class SuccessApiResult<out T>(
    val data: T
) : NetworkResult<T>()