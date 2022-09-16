package io.digikraft.domain.dao

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import io.digikraft.base.room.BaseDao
import io.digikraft.domain.model.cart.CartEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class CartDao : BaseDao<CartEntity>() {

    @get:Query("SELECT * FROM " + CartEntity.TABLE_NAME)
    abstract val vendorProducts: LiveData<List<CartEntity>>

    @Query("SELECT * FROM " + CartEntity.TABLE_NAME + " WHERE event_id == :eventId")
    abstract fun cartEventLive(eventId: Int): LiveData<List<CartEntity>>

    @Query("SELECT * FROM " + CartEntity.TABLE_NAME + " WHERE event_id == :eventId")
    abstract fun cartEventList(eventId: Int): List<CartEntity>

    @Query("SELECT * FROM " + CartEntity.TABLE_NAME + " WHERE event_id == :eventId")
    abstract fun cartEventFlow(eventId: Int): Flow<List<CartEntity>>

    @get:Query("SELECT COUNT(id) FROM " + CartEntity.TABLE_NAME)
    abstract val getCountOfItems: Long

    @get:Query("SELECT * FROM table_cart WHERE quantity != 0")
    abstract val cart: Flow<List<CartEntity>>

    @Query("SELECT * FROM table_cart")
    abstract fun getPaging(): PagingSource<Int, CartEntity>

    @Query("DELETE FROM table_cart WHERE id = :id")
    abstract fun delete(id: String)

    @Query("DELETE FROM table_cart")
    abstract fun clearAll()
}
