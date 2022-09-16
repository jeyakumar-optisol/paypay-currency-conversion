package io.digikraft.data.repository.local

import io.digikraft.data.database.room.RoomManager
import io.digikraft.domain.datasource.IRoomDatabaseRepository
import javax.inject.Inject

/*Room database repository*/
class RoomDatabaseRepository @Inject constructor(private val roomManager: RoomManager) :
    IRoomDatabaseRepository {
    override val cartDao get() = roomManager.cartDao
}