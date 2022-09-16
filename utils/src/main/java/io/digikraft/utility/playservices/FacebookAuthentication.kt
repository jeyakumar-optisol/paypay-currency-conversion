package io.digikraft.utility.playservices

import androidx.fragment.app.FragmentActivity
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import io.digikraft.domain.model.FacebookSignInModel
import io.digikraft.utility.debug.Log

open class FacebookAuthentication(private val fragmentActivity: FragmentActivity) {
    private val callbackManager: CallbackManager by lazy {
        CallbackManager.Factory.create()
    }

    private val loginManager by lazy { LoginManager.getInstance() }

    private var connectivityEvent: ((FacebookSignInModel) -> Unit)? = null

    fun build(): FacebookAuthentication {
        loginManager.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onCancel() {
                Log.d("FacebookAuthentication", "action: user cancel")
            }

            override fun onError(error: FacebookException) {
                error.localizedMessage?.let { it1 ->
                    Log.e("FacebookAuthentication", it1)
                    connectivityEvent?.invoke(FacebookSignInModel(error = it1))
                }
            }

            override fun onSuccess(result: LoginResult) {
                retrieveSessionDetails(result.accessToken) { result ->
                    connectivityEvent?.invoke(result)
                }
            }
        })
        return this
    }

    fun login(callback: (FacebookSignInModel) -> Unit) {
        connectivityEvent = callback

        loginManager.logOut()
        loginManager.logInWithReadPermissions(
            fragmentActivity,
            callbackManager,
            listOf("public_profile", "email")
        )
    }

    fun destroy() {
        connectivityEvent = null
        loginManager.unregisterCallback(callbackManager)
    }

    private fun retrieveSessionDetails(
        accessToken: AccessToken,
        callback: (FacebookSignInModel) -> Unit
    ) {
        val request = GraphRequest.newMeRequest(
            accessToken
        ) { obj, response ->
            try {
                val currentProfile = Profile.getCurrentProfile()
                val email = obj?.optString("email") ?: error("null_email")
                val firstName = currentProfile?.firstName ?: error("first_name")
                val lastName = currentProfile?.lastName ?: error("lastName")
                val token = accessToken.token

                callback(
                    FacebookSignInModel(
                        email = email,
                        firstName = firstName,
                        lastName = lastName,
                        token = token
                    )
                )
            } catch (e: Exception) {
                e.localizedMessage?.let { Log.e("retrieveSessionDetails", it) }
                callback(FacebookSignInModel(error = e.localizedMessage))
            }
        }
        request.executeAsync()
    }
}