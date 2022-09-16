package io.digikraft.domain.model.event.opening_hours

import android.os.Parcel
import android.os.Parcelable

data class EventOpeningHoursItem(
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val deletedAt: String? = null,
    val eventId: Int,
    val weekday: String? = null,
    val date: String? = null,
    val timeFrom: String? = null,
    val timeTo: String? = null,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(createdAt)
        parcel.writeString(updatedAt)
        parcel.writeString(deletedAt)
        parcel.writeInt(eventId)
        parcel.writeString(weekday)
        parcel.writeString(date)
        parcel.writeString(timeFrom)
        parcel.writeString(timeTo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EventOpeningHoursItem> {
        override fun createFromParcel(parcel: Parcel): EventOpeningHoursItem {
            return EventOpeningHoursItem(parcel)
        }

        override fun newArray(size: Int): Array<EventOpeningHoursItem?> {
            return arrayOfNulls(size)
        }
    }
}