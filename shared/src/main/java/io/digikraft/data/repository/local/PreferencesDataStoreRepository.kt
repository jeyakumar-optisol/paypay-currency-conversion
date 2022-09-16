package io.digikraft.data.repository.local

import androidx.datastore.core.DataStore
import io.digikraft.domain.datasource.IPreferenceDataStoreRepository
import io.digikraft.domain.model.proto.UserProfile
import javax.inject.Inject

/*
* when proto.userprofile defined it has several members and it will be updated with async call
* here it can be done through suspend call
* */
class PreferencesDataStoreRepository @Inject constructor(private val userProfileDataStore: DataStore<UserProfile>) :
    IPreferenceDataStoreRepository {

    override val userProfile get() = userProfileDataStore.data

    override suspend fun updateUserProfile(callback: (UserProfile.Builder) -> UserProfile.Builder) {
        userProfileDataStore.updateData {
            callback(it.toBuilder()).build()
        }
    }

    override suspend fun clearUserProfile(callback: (() -> Unit)?) {
        userProfileDataStore.updateData {
            it.toBuilder().clear().build().also {
                callback?.invoke() //when execution finish
            }
        }
    }
}