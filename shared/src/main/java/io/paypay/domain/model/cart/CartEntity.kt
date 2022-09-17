package io.paypay.domain.model.cart

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import io.paypay.base.room.BaseEntity


@Entity(tableName = CartEntity.TABLE_NAME)
class CartEntity : BaseEntity() {
    object Fields {
        const val ID = "id"
        const val EVENT_ID = "event_id"
        const val PRICE = "price"
        const val NAME = "name"
        const val QUANTITY = "quantity"
        const val TYPE = "type"
    }

    @PrimaryKey
    @SerializedName(Fields.ID)
    @ColumnInfo(name = Fields.ID)
    var id: Int = -1

    @SerializedName(Fields.EVENT_ID)
    @ColumnInfo(name = Fields.EVENT_ID)
    var eventId: Int = -1

    @SerializedName(Fields.PRICE)
    @ColumnInfo(name = Fields.PRICE)
    var price: String? = null

    @SerializedName(Fields.NAME)
    @ColumnInfo(name = Fields.NAME)
    var name: String? = null

    @SerializedName(Fields.QUANTITY)
    @ColumnInfo(name = Fields.QUANTITY)
    var quantity: Int = 0

    @SerializedName(Fields.TYPE)
    @ColumnInfo(name = Fields.TYPE)
    var type: String? = null

    companion object {
        const val TABLE_NAME = "table_cart"
    }
}

