package io.digikraft.data.database.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import io.digikraft.domain.model.UserSession
import io.digikraft.domain.model.proto.UserProfile


import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStoreManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val userProfileSerializer: UserProfileSerializer,
    private val userSessionSerializer: UserSessionSerializer
) {
    private val fileNameProfileDataStore = "user_profile.pb"
    private val fileNameUserSessionDataStore = "user_session.pb"

    private val Context.userProfileDataStore: DataStore<UserProfile> by dataStore(
        fileName = fileNameProfileDataStore,
        serializer = userProfileSerializer
    )

    val userProfileDataStore: DataStore<UserProfile> get() = context.userProfileDataStore

    private val Context.userSessionDataStore: DataStore<UserSession> by dataStore(
        fileName = fileNameUserSessionDataStore,
        serializer = userSessionSerializer
    )

    val userSessionDataStore: DataStore<UserSession> get() = context.userSessionDataStore
}