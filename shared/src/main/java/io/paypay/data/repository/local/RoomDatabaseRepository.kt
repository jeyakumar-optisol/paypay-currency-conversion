package io.paypay.data.repository.local

import io.paypay.data.database.room.RoomManager
import io.paypay.domain.datasource.IRoomDatabaseRepository
import javax.inject.Inject

/*Room database repository*/
class RoomDatabaseRepository @Inject constructor(private val roomManager: RoomManager) :
    IRoomDatabaseRepository {
    override val currencyDao get() = roomManager.currencyDao
}