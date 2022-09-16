package io.digikraft.data.repository.remote

import io.digikraft.domain.datasource.IProfileRepository
import io.digikraft.domain.datasource.services.ProfileApiService
import io.digikraft.domain.model.profile.ProfileItem
import io.digikraft.domain.model.profile.UpdateUserProfileRequest

class DefaultProfileRepository(private val profileApiService: ProfileApiService) : IProfileRepository {

    override suspend fun fetchUserProfile(token: String): Result<ProfileItem> =
        profileApiService.fetchUserProfile(token)

    override suspend fun updateUserProfile(token: String, updateUserProfileRequest: UpdateUserProfileRequest): Result<ProfileItem> =
        profileApiService.updateUserProfile(token, updateUserProfileRequest)


}