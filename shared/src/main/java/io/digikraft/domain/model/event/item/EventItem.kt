package io.digikraft.domain.model.event.item

import android.os.Parcel
import android.os.Parcelable
import io.digikraft.domain.model.event.Coordinate
import io.digikraft.domain.model.event.opening_hours.EventOpeningHoursItem

data class EventItem(
    val id: Int,
    val createdAt: String? = null,
    val address: String? = null,
    val description: String? = null,
    val googleMapInfo: String? = null,
    val imageUrl: String? = null,
    val latitude: String? = null,
    val longitude: String? = null,
    val name: String? = null,
    val siteUrl: String? = null,
    val typeId: Int,
    val coordinates: Coordinate? = null,
    val dates: ArrayList<String>? = null,
    val openingHours: ArrayList<EventOpeningHoursItem>? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readParcelable(Coordinate::class.java.classLoader),
        parcel.createStringArrayList(),
        parcel.createTypedArrayList(EventOpeningHoursItem)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(createdAt)
        parcel.writeString(address)
        parcel.writeString(description)
        parcel.writeString(googleMapInfo)
        parcel.writeString(imageUrl)
        parcel.writeString(latitude)
        parcel.writeString(longitude)
        parcel.writeString(name)
        parcel.writeString(siteUrl)
        parcel.writeInt(typeId)
        parcel.writeParcelable(coordinates, flags)
        parcel.writeStringList(dates)
        parcel.writeTypedList(openingHours)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EventItem> {
        override fun createFromParcel(parcel: Parcel): EventItem {
            return EventItem(parcel)
        }

        override fun newArray(size: Int): Array<EventItem?> {
            return arrayOfNulls(size)
        }
    }

}