package io.digikraft.domain.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

@Keep
data class GoogleSignInModel(
    var email: String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var error: String? = null,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(email)
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeString(error)
    }

    override fun describeContents(): Int {
        return 0
    }

    fun setSignInAccount(result: GoogleSignInAccount): GoogleSignInModel {
        this.email = result.email
        this.firstName = result.givenName?:result.displayName
        this.lastName = result.familyName
        return this
    }

    companion object CREATOR : Parcelable.Creator<GoogleSignInModel> {
        override fun createFromParcel(parcel: Parcel): GoogleSignInModel {
            return GoogleSignInModel(parcel)
        }

        override fun newArray(size: Int): Array<GoogleSignInModel?> {
            return arrayOfNulls(size)
        }
    }


}