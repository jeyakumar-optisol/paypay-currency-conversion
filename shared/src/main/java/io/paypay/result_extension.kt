package io.paypay

import io.paypay.result.ErrorApiResult
import io.paypay.result.NetworkResult
import io.paypay.result.SuccessApiResult
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.internal.resumeCancellableWith
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Response


//suspend fun <T : Any> getResult(data: suspend () -> Response<T>): NetworkResult<T> {
//    return if (data().isSuccessful) {
//        resultOf { data().body() }
//    } else {
//        ErrorApiResult(getErrorMessage(data().code()))
//    }
////    return resultOf { data().getOrThrow() }
//}

suspend fun <T : Any> getResult(data: suspend () -> Result<T>): NetworkResult<T> {
    return try {
        resultOf { data().getOrThrow() }
    } catch (e: Exception) {
        ErrorApiResult(e.localizedMessage ?: "Error occurred")
    }
}

inline fun <R> resultOf(block: () -> R): NetworkResult<R> {
    return try {
        SuccessApiResult(block())
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        ErrorApiResult(e.message.toString())
    }
}

inline fun <T, R> T.resultOf(block: T.() -> R): NetworkResult<R> {
    return try {
        SuccessApiResult(block())
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        ErrorApiResult(e.message.toString())
    }
}

private fun getErrorMessage(code: Int, message: String? = null): String {
    return when (code) {
        ErrorCodes.SocketTimeOut.code -> "Timeout"
        401 -> "Unauthorised"
        404 -> "Not found"
        500 -> "Internal server error"
        else -> "Message: $message"
    }
}

enum class ErrorCodes(val code: Int) {
    SocketTimeOut(-1)
}