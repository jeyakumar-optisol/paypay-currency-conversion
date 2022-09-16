package io.digikraft.photosapp.ui.sign_up

import android.app.Application
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.digikraft.di.IoDispatcher
import io.digikraft.domain.model.signin.CreateFirebaseUserModel
import io.digikraft.domain.usecase.CreateFirebaseUserUseCase
import io.digikraft.result.ErrorApiResult
import io.digikraft.result.SuccessApiResult
import io.digikraft.ui.base.BaseViewModel
import io.digikraft.utility.debug.Log
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    application: Application, @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val createFirebaseUserUseCase: CreateFirebaseUserUseCase
) : BaseViewModel(application) {

    override fun onCreate() {
        //noop
    }

    fun createFirebaseUser(createFirebaseUserModel: CreateFirebaseUserModel) =
        liveData(ioDispatcher) {
            showProgressDialog("Signing up..")
            createFirebaseUserUseCase.invoke(createFirebaseUserModel).collect {
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
}