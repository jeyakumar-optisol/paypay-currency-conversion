/*
package io.digikraft.domain.model.vendor

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import io.digikraft.base.room.BaseEntity


*/
/*
* please follow naming convention as BaseEntity
* *//*

@Entity(tableName = VendorProductEntity.TABLE_NAME) //fixme: todo: clear if unnecessary
class VendorProductEntity : BaseEntity() {
    object Fields {
        const val ID = "id"
        const val PRICE = "price"
        const val NAME = "name"
        const val DESCRIPTION = "description"
        const val TYPE = "type"
        const val CART = "cart"
    }

    @PrimaryKey
    @SerializedName(Fields.ID)
    @ColumnInfo(name = Fields.ID)
    var id: Int = -1

    @SerializedName(Fields.PRICE)
    @ColumnInfo(name = Fields.PRICE)
    var price: String? = null

    @SerializedName(Fields.NAME)
    @ColumnInfo(name = Fields.NAME)
    var name: String? = null

    @SerializedName(Fields.DESCRIPTION)
    @ColumnInfo(name = Fields.DESCRIPTION)
    var description: String? = null

    @SerializedName(Fields.TYPE)
    @ColumnInfo(name = Fields.TYPE)
    var type: Int = 1 // 0 - for header

    @SerializedName(Fields.CART)
    @ColumnInfo(name = Fields.CART)
    var cart: Int = 0 // 0 - for header

    companion object {
        const val TABLE_NAME = "table_vendor_product"
    }
}

*/
