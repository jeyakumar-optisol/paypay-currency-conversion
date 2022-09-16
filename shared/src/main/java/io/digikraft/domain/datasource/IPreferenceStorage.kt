package io.digikraft.domain.datasource

import kotlinx.coroutines.flow.Flow

interface IPreferenceStorage {

    suspend fun saveToken(token: String)
    val token: Flow<String>

    suspend fun saveLoginState(logged: Boolean)
    val logged: Flow<Boolean>

}