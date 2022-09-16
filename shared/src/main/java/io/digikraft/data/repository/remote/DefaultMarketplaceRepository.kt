package io.digikraft.data.repository.remote

import io.digikraft.domain.datasource.IMarketplaceRepository
import io.digikraft.domain.datasource.services.MarketplaceApiService
import io.digikraft.domain.model.event.EventsResponse

class DefaultMarketplaceRepository(private val apiService: MarketplaceApiService) : IMarketplaceRepository {

    override suspend fun fetchMarketplaceEvents(token: String, afterCursor: String): Result<EventsResponse>  =
        apiService.fetchMarketplaceEvents(token, afterCursor)
}