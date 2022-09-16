package io.digikraft.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*Write a migration rules
* https://medium.com/androiddevelopers/datastore-and-data-migration-fdca806eb1aa
* */
@Serializable
data class UserSession(
    @SerialName("token") var token: String? = null,
)