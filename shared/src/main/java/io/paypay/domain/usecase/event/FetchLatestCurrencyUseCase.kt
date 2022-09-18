package io.paypay.domain.usecase.event

import io.paypay.base.FlowUseCase
import io.paypay.di.IoDispatcher
import io.paypay.domain.dao.CurrencyDao
import io.paypay.domain.datasource.IEventRepository
import io.paypay.domain.datasource.IPreferenceStorage
import io.paypay.domain.model.ResponseLatestCurrency
import io.paypay.domain.model.cart.CurrencyEntity
import io.paypay.getResult
import io.paypay.result.NetworkResult
import io.paypay.result.SuccessApiResult
import io.paypay.shared.BuildConfig
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FetchLatestCurrencyUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val currencyDao: CurrencyDao,
    private val repository: IEventRepository,
    private val iPreferenceStorage: IPreferenceStorage
) : FlowUseCase<Unit, ResponseLatestCurrency>() {

    override fun performAction(parameters: Unit): Flow<NetworkResult<ResponseLatestCurrency>> {
        return flow {
            val result: NetworkResult<ResponseLatestCurrency> = getResult {
                repository.fetchCurrency(BuildConfig.APP_ID)
            }
            if (result is SuccessApiResult) {
                val currencyEntityList = mutableListOf<CurrencyEntity>()
                result.data.rates.keySet().map {
                    val currencyEntity = CurrencyEntity()
                    currencyEntity.currency = it
                    currencyEntity.price = result.data.rates[it].asString
                    currencyEntityList.add(currencyEntity)
                }
                currencyDao.insertAll(currencyEntityList)
                iPreferenceStorage.saveLastUpdate(System.currentTimeMillis().toString())
            }
            emit(result)
        }.flowOn(ioDispatcher)
    }

}