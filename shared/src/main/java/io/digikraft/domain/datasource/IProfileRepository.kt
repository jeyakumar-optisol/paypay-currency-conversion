package io.digikraft.domain.datasource

import io.digikraft.domain.model.profile.ProfileItem
import io.digikraft.domain.model.profile.UpdateUserProfileRequest

interface IProfileRepository {

    suspend fun fetchUserProfile(token: String): Result<ProfileItem>

    suspend fun updateUserProfile(token: String, updateUserProfileRequest: UpdateUserProfileRequest): Result<ProfileItem>

}