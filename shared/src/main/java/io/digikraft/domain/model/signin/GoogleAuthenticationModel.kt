package io.digikraft.domain.model.signin

import com.google.firebase.auth.AuthCredential
import io.digikraft.domain.model.GoogleSignInModel

data class GoogleAuthenticationModel(
    val googleSignInModel: GoogleSignInModel,
    val authCredential: AuthCredential? = null
)