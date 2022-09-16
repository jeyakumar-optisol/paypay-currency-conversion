/*
 * Copyright 2021 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.digikraft.di

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.preferencesDataStore
import com.google.crypto.tink.Aead
import com.google.crypto.tink.KeyTemplates
import com.google.crypto.tink.aead.AeadConfig
import com.google.crypto.tink.integration.android.AndroidKeysetManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.digikraft.SecurityUtil
import io.digikraft.data.database.datastore.DataStoreManager
import io.digikraft.data.database.room.RoomManager
import io.digikraft.data.repository.local.DataStorePreferenceStorage
import io.digikraft.data.repository.local.PreferencesDataStoreRepository
import io.digikraft.data.repository.local.RoomDatabaseRepository
import io.digikraft.data.repository.local.UserSessionDataStoreRepository
import io.digikraft.domain.dao.CartDao
import io.digikraft.domain.datasource.IPreferenceDataStoreRepository
import io.digikraft.domain.datasource.IPreferenceStorage
import io.digikraft.domain.datasource.IRoomDatabaseRepository
import io.digikraft.domain.datasource.IUserSessionDataStoreRepository
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object PreferencesStorageModule {
    private const val KEYSET_NAME = "master_keyset"
    private const val PREFERENCE_FILE = "master_key_preference"
    private const val MASTER_KEY_URI = "android-keystore://master_key"

    val Context.dataStore by preferencesDataStore(
        name = DataStorePreferenceStorage.PREFS_NAME,
        produceMigrations = { context ->
            listOf(
                SharedPreferencesMigration(
                    context,
                    DataStorePreferenceStorage.PREFS_NAME
                )
            )
        }
    )

    @Singleton
    @Provides
    fun provideAead(application: Application): Aead {
        AeadConfig.register()

        return AndroidKeysetManager.Builder()
            .withSharedPref(application, KEYSET_NAME, PREFERENCE_FILE)
            .withKeyTemplate(KeyTemplates.get("AES256_GCM"))
            .withMasterKeyUri(MASTER_KEY_URI)
            .build()
            .keysetHandle
            .getPrimitive(Aead::class.java)
    }

    @Singleton
    @Provides
    fun provideSecurityUtils(aead: Aead): SecurityUtil {
        return SecurityUtil(aead)
    }

    @Provides
    @Singleton
    fun provideCartDao(roomManager: RoomManager): CartDao {
        return roomManager.cartDao
    }

    @Provides
    @Singleton
    fun provideRoomDatabaseRepository(roomManager: RoomManager): IRoomDatabaseRepository {
        return RoomDatabaseRepository(roomManager)
    }

    @Provides
    @Singleton
    fun provideRoomManager(@ApplicationContext appContext: Context): RoomManager {
        return RoomManager.getInstance(appContext)
    }

    @Provides
    @Singleton
    fun provideDataStoreRepository(dataStoreManager: DataStoreManager): IPreferenceDataStoreRepository {
        return PreferencesDataStoreRepository(dataStoreManager.userProfileDataStore)
    }

    @Provides
    fun provideUserSessionDataStoreRepository(dataStoreManager: DataStoreManager): IUserSessionDataStoreRepository {
        return UserSessionDataStoreRepository(dataStoreManager.userSessionDataStore)
    }

    @Singleton
    @Provides
    fun providePreferenceStorage(
        @ApplicationContext context: Context,
        securityUtil: SecurityUtil
    ): IPreferenceStorage =
        DataStorePreferenceStorage(context.dataStore, securityUtil)

}
