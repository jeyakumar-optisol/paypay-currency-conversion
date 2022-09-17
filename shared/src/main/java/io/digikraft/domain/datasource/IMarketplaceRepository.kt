package io.digikraft.domain.datasource

import io.digikraft.domain.model.event.EventsResponse

interface IMarketplaceRepository {

    suspend fun fetchMarketplaceEvents(afterCursor: String): Result<EventsResponse>

}