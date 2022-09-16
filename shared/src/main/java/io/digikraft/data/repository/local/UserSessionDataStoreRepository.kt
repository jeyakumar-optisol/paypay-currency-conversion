package io.digikraft.data.repository.local

import androidx.datastore.core.DataStore
import io.digikraft.domain.datasource.IUserSessionDataStoreRepository
import io.digikraft.domain.model.UserSession
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/*
* when proto.userprofile defined it has several members and it will be updated with async call
* here it can be done through suspend call
* */
class UserSessionDataStoreRepository @Inject constructor(private val userSessionDataStore: DataStore<UserSession>) :
    IUserSessionDataStoreRepository {

    override suspend fun updateUserSession(callback: (UserSession) -> UserSession) {
        userSessionDataStore.updateData {
            callback(it)
        }
    }

    override suspend fun clearUserSession(callback: (() -> Unit)?) {
        userSessionDataStore.updateData {
            UserSession().also {
                callback?.invoke()
            }
        }
    }

    override val userSession: Flow<UserSession>
        get() = userSessionDataStore.data
}