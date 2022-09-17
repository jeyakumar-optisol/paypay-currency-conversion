package io.paypay.base

import io.paypay.result.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

//P - request parameter
//T - response

abstract class UseCase<in P, T>(private val coroutineDispatcher: CoroutineDispatcher) {

    suspend operator fun invoke(parameters: P): NetworkResult<T> {
        return withContext(coroutineDispatcher) {
            performAction(parameters)
        }
    }

    protected abstract suspend fun performAction(parameters: P): NetworkResult<T>

}