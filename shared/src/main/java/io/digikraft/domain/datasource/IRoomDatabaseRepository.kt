package io.digikraft.domain.datasource

import io.digikraft.domain.dao.CartDao

interface IRoomDatabaseRepository {
    val cartDao: CartDao
}