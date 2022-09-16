package io.digikraft.data.repository.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import io.digikraft.data.repository.local.DataStorePreferenceStorage.PreferencesKeys.PREF_API_TOKEN
import io.digikraft.domain.datasource.IPreferenceStorage
import io.digikraft.SecurityUtil
import io.digikraft.data.repository.local.DataStorePreferenceStorage.PreferencesKeys.PREF_LOGIN_STATE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DataStorePreferenceStorage @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val security: SecurityUtil
) : IPreferenceStorage {

    companion object {
        const val PREFS_NAME = "blix"
    }

    object PreferencesKeys {
        val PREF_API_TOKEN = stringPreferencesKey("pref_api_token")
        val PREF_LOGIN_STATE = booleanPreferencesKey("pref_login_state")
        val PREF_LANGUAGE = stringPreferencesKey("pref_language")
    }

    override suspend fun saveToken(token: String) {
        dataStore.secureEdit(token) { prefs, encryptedValue ->
            prefs[PREF_API_TOKEN] = encryptedValue
        }
    }

    override val token: Flow<String>
        get() = dataStore.data.secureMap { it[PREF_API_TOKEN].orEmpty() }

    override suspend fun saveLoginState(logged: Boolean) {
        dataStore.edit { it[PREF_LOGIN_STATE] = logged }
    }

    override val logged: Flow<Boolean>
        get() = dataStore.data.map { it[PREF_LOGIN_STATE] ?: false }


    private inline fun <reified T> Flow<Preferences>.secureMap(crossinline fetchValue: (value: Preferences) -> String): Flow<T> {
        return map {
            security.decrypt(fetchValue(it)) as T
        }
    }

    private suspend inline fun <reified T> DataStore<Preferences>.secureEdit(
        value: T,
        crossinline editStore: (MutablePreferences, String) -> Unit
    ) {
        edit {
            val encryptedValue = security.encrypt(value.toString())
            editStore.invoke(it, encryptedValue)
        }
    }

}