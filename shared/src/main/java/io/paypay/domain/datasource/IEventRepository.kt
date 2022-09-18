package io.paypay.domain.datasource

import io.paypay.domain.model.ResponseLatestCurrency

interface IEventRepository {
    suspend fun fetchCurrency(appId: String): Result<ResponseLatestCurrency>
}