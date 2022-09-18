package io.paypay.data.repository.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import io.paypay.SecurityUtil
import io.paypay.data.repository.local.DataStorePreferenceStorage.PreferencesKeys.PREF_LAST_UPDATE
import io.paypay.domain.datasource.IPreferenceStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DataStorePreferenceStorage @Inject constructor(
    private val dataStore: DataStore<Preferences>, private val security: SecurityUtil
) : IPreferenceStorage {

    companion object {
        const val PREFS_NAME = "paypay"
    }

    object PreferencesKeys {
        val PREF_LAST_UPDATE = stringPreferencesKey("pref_last_update")
    }

    override suspend fun saveLastUpdate(millis: String) {
        dataStore.secureEdit(millis) { prefs, encryptedValue ->
            prefs[PREF_LAST_UPDATE] = encryptedValue
        }
    }

    override val lastUpdate: Flow<String>
        get() = dataStore.data.secureMap { it[PREF_LAST_UPDATE].orEmpty() }


    private inline fun <reified T> Flow<Preferences>.secureMap(crossinline fetchValue: (value: Preferences) -> String): Flow<T> {
        return map {
            security.decrypt(fetchValue(it)) as T
        }
    }

    private suspend inline fun <reified T> DataStore<Preferences>.secureEdit(
        value: T, crossinline editStore: (MutablePreferences, String) -> Unit
    ) {
        edit {
            val encryptedValue = security.encrypt(value.toString())
            editStore.invoke(it, encryptedValue)
        }
    }

}