package io.paypay.domain.datasource

import io.paypay.domain.dao.CartDao

interface IRoomDatabaseRepository {
    val cartDao: CartDao
}