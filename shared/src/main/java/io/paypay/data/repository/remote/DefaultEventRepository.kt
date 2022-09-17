package io.paypay.data.repository.remote

import io.paypay.domain.datasource.IEventRepository
import io.paypay.domain.datasource.services.EventApiService
import io.paypay.domain.model.ResponseLatestCurrency

class DefaultEventRepository(private val eventApiService: EventApiService) : IEventRepository {

    override suspend fun fetchEvents(afterCursor: String): Result<ResponseLatestCurrency> =
        eventApiService.fetchLatestCurrency(afterCursor)
}