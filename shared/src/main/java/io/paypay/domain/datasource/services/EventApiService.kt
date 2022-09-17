package io.paypay.domain.datasource.services

import io.paypay.domain.model.ResponseLatestCurrency
import retrofit2.http.GET
import retrofit2.http.Query

interface EventApiService {
    @GET("latest.json")
    suspend fun fetchLatestCurrency(@Query("app_id") appId: String): Result<ResponseLatestCurrency>

}