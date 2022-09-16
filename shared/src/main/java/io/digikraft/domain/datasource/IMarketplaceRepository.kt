package io.digikraft.domain.datasource

import io.digikraft.domain.model.event.EventsResponse

interface IMarketplaceRepository {

    suspend fun fetchMarketplaceEvents(token: String, afterCursor: String): Result<EventsResponse>

}