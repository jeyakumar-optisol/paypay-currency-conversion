package io.digikraft.domain.datasource

import io.digikraft.domain.model.profile.ProfileItem
import io.digikraft.domain.model.profile.UpdateUserProfileRequest

interface IProfileRepository {

    suspend fun fetchUserProfile(): Result<ProfileItem>

    suspend fun updateUserProfile(updateUserProfileRequest: UpdateUserProfileRequest): Result<ProfileItem>

}