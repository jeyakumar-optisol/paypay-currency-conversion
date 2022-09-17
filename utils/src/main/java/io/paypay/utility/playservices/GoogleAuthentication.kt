package io.paypay.utility.playservices

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.util.ExponentialBackOff
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import io.paypay.domain.model.GoogleSignInModel
import io.paypay.utility.nullablity.orElse


open class GoogleAuthentication(
    private val fragmentLaunch: ActivityResultLauncher<Intent>?,
    private val fragment: Fragment,
    private val authClientId: String?
) {

    private val context get() = fragment.activity!!
    private var connectivityEvent: ((GoogleSignInModel) -> Unit)? = null

    /**
     * invoke signIn option
     **/
    fun signIn(
        signOut: Boolean = true,
        callback: ((GoogleSignInModel) -> Unit),
    ) {
        connectivityEvent = callback
        if (signOut) {
            getSignInIntent().signOut()
        }
        val account = getLastSignedAccount()
        account?.let {
            callback(GoogleSignInModel(error = "account already connected"))
            connectivityEvent = null
        }.orElse {
            fragmentLaunch?.launch(getSignInIntent().signInIntent)
        }
    }

    fun signOut(callback: (exception: Exception?) -> Unit) {
        getSignInIntent().signOut().addOnCompleteListener {
            if (it.isSuccessful) {
                connectivityEvent?.invoke(GoogleSignInModel(error = "signed_out"))
                callback(null)
            } else {
                callback(it.exception!!)
            }
        }
    }

    private fun getLastSignedAccount(): GoogleSignInAccount? {
        return GoogleSignIn.getLastSignedInAccount(context)
    }

    private fun getSignInIntent(): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
        authClientId?.let {
            gso.requestIdToken(authClientId)
        }
        return GoogleSignIn.getClient(context, gso.build())
    }

    fun getCredentials(list: List<String>): GoogleAccountCredential? {
        return getLastSignedAccount()?.let {
            GoogleAccountCredential.usingOAuth2(
                context, list
            )
                .setSelectedAccountName(it.account?.name)
                .setBackOff(ExponentialBackOff())
        }.orElse {
            null
        }
    }

    fun getAuthCredential(): AuthCredential? {
        return getLastSignedAccount()?.let {
            GoogleAuthProvider.getCredential(it.idToken, null)
        }.orElse {
            null
        }
    }

    fun isAuthenticated(): Boolean {
        return getLastSignedAccount() != null
    }

    fun setAuthResult(result: GoogleSignInAccount) {
        connectivityEvent?.invoke(GoogleSignInModel().setSignInAccount(result)) //fixme: callback null
    }
}