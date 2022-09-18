package io.paypay.domain.datasource

import kotlinx.coroutines.flow.Flow

interface IPreferenceStorage {

    suspend fun saveLastUpdate(millis: String)
    val lastUpdate: Flow<String>
}