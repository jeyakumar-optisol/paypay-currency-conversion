package io.digikraft.photosapp.ui.sign_in_email

import android.app.Application
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.digikraft.di.IoDispatcher
import io.digikraft.domain.model.signin.EmailAuthenticationModel
import io.digikraft.domain.usecase.SaveUserLoginStateUseCase
import io.digikraft.domain.usecase.sign_in.SignInWithEmailFirebaseUseCase
import io.digikraft.result.ErrorApiResult
import io.digikraft.result.SuccessApiResult
import io.digikraft.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class SignInEmailViewModel @Inject constructor(
    application: Application,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val signInWithEmailFirebaseUseCase: SignInWithEmailFirebaseUseCase,
    private val saveUserLoginStateUseCase: SaveUserLoginStateUseCase
) : BaseViewModel(application) {

    override fun onCreate() {
        //noop
    }

    fun authenticateFirebase(emailAuthenticationModel: EmailAuthenticationModel) = liveData(ioDispatcher) {
        showProgressDialog("Logging in")
        signInWithEmailFirebaseUseCase.invoke(emailAuthenticationModel).collect {
            when (it) {
                is SuccessApiResult -> {
                    val success = saveUserLoginStateUseCase.invoke(true)
                    hideProgressDialog()
                    emit(success)
                }
                is ErrorApiResult -> {
                    hideProgressDialog()
                    errorApiResult.emit(it)
                }
            }
        }
    }

    fun saveUserLoginState(loginState: Boolean) = liveData(ioDispatcher) {
        emit(saveUserLoginStateUseCase.invoke(loginState))
    }

}