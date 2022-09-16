package io.digikraft.domain.datasource.services

import io.digikraft.domain.model.event.EventsResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MarketplaceApiService {

    @GET("v1/marketplace/events")
    suspend fun fetchMarketplaceEvents(
        @Query("afterCursor") afterCursor: String
    ): Result<EventsResponse>

}