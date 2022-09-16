package io.digikraft.domain.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep

@Keep
data class ResetPasswordModel(
    var email: String
) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString()?:"") {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(email)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ResetPasswordModel> {
        override fun createFromParcel(parcel: Parcel): ResetPasswordModel {
            return ResetPasswordModel(parcel)
        }

        override fun newArray(size: Int): Array<ResetPasswordModel?> {
            return arrayOfNulls(size)
        }
    }

}