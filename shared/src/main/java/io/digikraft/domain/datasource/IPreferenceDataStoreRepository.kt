package io.digikraft.domain.datasource

import io.digikraft.domain.model.proto.UserProfile
import kotlinx.coroutines.flow.Flow

interface IPreferenceDataStoreRepository {

    val userProfile: Flow<UserProfile>
    suspend fun updateUserProfile(callback: (UserProfile.Builder) -> UserProfile.Builder)
    suspend fun clearUserProfile(callback: (() -> Unit)? = null)
}