package io.paypay.domain.datasource

import kotlinx.coroutines.flow.Flow

interface IPreferenceStorage {

    suspend fun saveToken(token: String)
    val token: Flow<String>
}