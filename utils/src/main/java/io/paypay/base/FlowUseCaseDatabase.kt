package io.paypay.base

import kotlinx.coroutines.flow.Flow

abstract class FlowUseCaseDatabase<in P, T> {

    operator fun invoke(parameters: P): Flow<T> {
        return performAction(parameters)
    }

    protected abstract fun performAction(parameters: P): Flow<T>

}