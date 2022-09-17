package io.digikraft.photosapp.ui.reset_password

import android.app.Application
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.digikraft.di.IoDispatcher
import io.digikraft.domain.model.ResetPasswordModel
import io.digikraft.domain.usecase.ResetPasswordFirebaseUseCase
import io.digikraft.result.ErrorApiResult
import io.digikraft.result.SuccessApiResult
import io.digikraft.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    application: Application,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val resetPasswordFirebaseUseCase: ResetPasswordFirebaseUseCase
) : BaseViewModel(application) {

    override fun onCreate() {

    }

    fun sendResetPasswordEmail(resetPasswordModel: ResetPasswordModel) = liveData(ioDispatcher) {
        showProgressDialog("Sending..")
        resetPasswordFirebaseUseCase.invoke(resetPasswordModel).collect {
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