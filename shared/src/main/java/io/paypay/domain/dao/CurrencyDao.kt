package io.paypay.domain.dao

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import io.paypay.base.room.BaseDao
import io.paypay.domain.model.cart.CurrencyEntity

import kotlinx.coroutines.flow.Flow

@Dao
abstract class CurrencyDao : BaseDao<CurrencyEntity>() {

    @get:Query("SELECT * FROM " + CurrencyEntity.TABLE_NAME)
    abstract val currencyListLiveData: LiveData<List<CurrencyEntity>>

    @Query("SELECT * FROM " + CurrencyEntity.TABLE_NAME + " WHERE currency == :currencyName")
    abstract fun cartEventLive(currencyName: String): LiveData<List<CurrencyEntity>>

    @Query("SELECT * FROM " + CurrencyEntity.TABLE_NAME + " WHERE currency == :currencyName")
    abstract fun cartEventList(currencyName: String): List<CurrencyEntity>

    @Query("SELECT * FROM " + CurrencyEntity.TABLE_NAME + " WHERE currency == :currencyName")
    abstract fun cartEventFlow(currencyName: String): Flow<List<CurrencyEntity>>

    @get:Query("SELECT COUNT(currency) FROM " + CurrencyEntity.TABLE_NAME)
    abstract val getCountOfItems: Long

    /*@get:Query("SELECT * FROM currency_table WHERE quantity != 0")
    abstract val cart: Flow<List<CurrencyEntity>>*/

    @Query("SELECT * FROM currency_table")
    abstract fun getPaging(): PagingSource<Int, CurrencyEntity>

    @Query("DELETE FROM currency_table WHERE currency = :currencyName")
    abstract fun delete(currencyName: String)

    @Query("DELETE FROM currency_table")
    abstract fun clearAll()
}
