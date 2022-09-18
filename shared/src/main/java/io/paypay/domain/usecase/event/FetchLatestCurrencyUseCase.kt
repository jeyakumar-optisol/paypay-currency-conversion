package io.paypay.domain.usecase.event

import io.paypay.base.FlowUseCase
import io.paypay.di.IoDispatcher
import io.paypay.domain.datasource.IEventRepository
import io.paypay.domain.datasource.IPreferenceStorage
import io.paypay.domain.model.ResponseLatestCurrency
import io.paypay.getResult
import io.paypay.result.NetworkResult
import io.paypay.shared.BuildConfig
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FetchLatestCurrencyUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val repository: IEventRepository
) : FlowUseCase<Unit, ResponseLatestCurrency>() {

    override fun performAction(parameters: Unit): Flow<NetworkResult<ResponseLatestCurrency>> {
        return flow {
            emit(getResult {
                repository.fetchCurrency(BuildConfig.APP_ID)
            })
        }.flowOn(ioDispatcher)
    }

}