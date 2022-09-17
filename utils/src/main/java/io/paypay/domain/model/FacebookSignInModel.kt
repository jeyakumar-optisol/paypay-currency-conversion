package io.paypay.domain.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FacebookAuthProvider

@Keep
data class FacebookSignInModel(
    var email: String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var token: String? = null,
    var error: String? = null,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
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
        parcel.writeString(token)
        parcel.writeString(error)
    }

    override fun describeContents(): Int {
        return 0
    }

    val authCredential: AuthCredential?
        get() {
            return token?.let { FacebookAuthProvider.getCredential(it) }
        }

    companion object CREATOR : Parcelable.Creator<FacebookSignInModel> {
        override fun createFromParcel(parcel: Parcel): FacebookSignInModel {
            return FacebookSignInModel(parcel)
        }

        override fun newArray(size: Int): Array<FacebookSignInModel?> {
            return arrayOfNulls(size)
        }
    }


}