package io.digikraft.base

import io.digikraft.result.NetworkResult
import kotlinx.coroutines.flow.Flow

abstract class FlowUseCase<in P, T> {

    operator fun invoke(parameters: P): Flow<NetworkResult<T>> {
        return performAction(parameters)
    }

    protected abstract fun performAction(parameters: P): Flow<NetworkResult<T>>

}