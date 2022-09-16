package io.digikraft.domain.datasource.services

import io.digikraft.domain.model.profile.ProfileItem
import io.digikraft.domain.model.profile.UpdateUserProfileRequest
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH

interface ProfileApiService {

    @GET("v1/users/profile")
    suspend fun fetchUserProfile(@Header("Authorization") token: String): Result<ProfileItem>

    @PATCH("v1/users/profile")
    suspend fun updateUserProfile(@Header("Authorization") token: String, updateUserProfileRequest: UpdateUserProfileRequest): Result<ProfileItem>


}