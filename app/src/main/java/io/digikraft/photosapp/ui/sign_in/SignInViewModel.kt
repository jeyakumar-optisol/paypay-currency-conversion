package io.digikraft.photosapp.ui.sign_in

import android.app.Application
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.digikraft.di.IoDispatcher
import io.digikraft.domain.model.FacebookSignInModel
import io.digikraft.domain.model.signin.GoogleAuthenticationModel
import io.digikraft.domain.usecase.SaveUserLoginStateUseCase
import io.digikraft.domain.usecase.sign_in.SignInWithFacebookAuthenticationFirebaseUseCase
import io.digikraft.domain.usecase.sign_in.SignInWithGoogleAuthenticationFirebaseUseCase
import io.digikraft.result.ErrorApiResult
import io.digikraft.result.SuccessApiResult
import io.digikraft.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    application: Application,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val googleAuthenticationFirebaseUseCase: SignInWithGoogleAuthenticationFirebaseUseCase,
    private val signInWithFacebookAuthenticationFirebaseUseCase: SignInWithFacebookAuthenticationFirebaseUseCase,
    private val saveUserLoginStateUseCase: SaveUserLoginStateUseCase
) : BaseViewModel(application) {

    override fun onCreate() {
        //noop
    }

    fun signInWithGoogle(googleAuthenticationModel: GoogleAuthenticationModel) =
        liveData(ioDispatcher) {
            showProgressDialog("Logging in")
            googleAuthenticationFirebaseUseCase.invoke(googleAuthenticationModel).collect {
                hideProgressDialog()
                when (it) {
                    is SuccessApiResult -> {
                        emit(it)
                    }
                    is ErrorApiResult -> {
                        errorApiResult.emit(it)
                    }
                }
            }
        }

    fun signInWithFacebook(facebookSignInModel: FacebookSignInModel) =
        liveData(ioDispatcher) {
            showProgressDialog("Logging in")
            signInWithFacebookAuthenticationFirebaseUseCase.invoke(facebookSignInModel).collect {
                hideProgressDialog()
                when (it) {
                    is SuccessApiResult -> {
                        emit(it)
                    }
                    is ErrorApiResult -> {
                        errorApiResult.emit(it)
                    }
                }
            }
        }

    fun saveUserLoginState(loginState: Boolean) = liveData(ioDispatcher) {
        emit(saveUserLoginStateUseCase.invoke(loginState))
    }
}