package io.paypay.domain.datasource

import io.paypay.domain.dao.CurrencyDao

interface IRoomDatabaseRepository {
    val currencyDao: CurrencyDao
}