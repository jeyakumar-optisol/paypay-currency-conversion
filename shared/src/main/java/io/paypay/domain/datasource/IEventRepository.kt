package io.paypay.domain.datasource

import io.paypay.domain.model.ResponseLatestCurrency

interface IEventRepository {
    suspend fun fetchEvents(afterCursor: String): Result<ResponseLatestCurrency>
}