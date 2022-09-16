package io.digikraft.domain.datasource

import io.digikraft.domain.model.UserSession
import kotlinx.coroutines.flow.Flow

interface IUserSessionDataStoreRepository {

    val userSession: Flow<UserSession>
    suspend fun updateUserSession(callback: (UserSession) -> UserSession)
    suspend fun clearUserSession(callback: (() -> Unit)?)
}