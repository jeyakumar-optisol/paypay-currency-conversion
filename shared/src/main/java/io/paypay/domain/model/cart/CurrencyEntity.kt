package io.paypay.domain.model.cart

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import io.paypay.base.room.BaseEntity


@Entity(tableName = CurrencyEntity.TABLE_NAME)
class CurrencyEntity : BaseEntity() {
    object Fields {
        const val PRICE = "price"
        const val CURRENCY = "currency"
    }

    @SerializedName(Fields.PRICE)
    @ColumnInfo(name = Fields.PRICE)
    var price: String? = null

    @PrimaryKey
    @NonNull
    @SerializedName(Fields.CURRENCY)
    @ColumnInfo(name = Fields.CURRENCY)
    var currency: String = ""

    companion object {
        const val TABLE_NAME = "currency_table"
    }
}

