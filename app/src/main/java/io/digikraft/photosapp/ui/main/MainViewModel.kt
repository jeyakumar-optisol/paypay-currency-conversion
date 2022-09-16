package io.digikraft.photosapp.ui.main

import android.app.Application
import dagger.hilt.android.lifecycle.HiltViewModel
import io.digikraft.domain.usecase.GetUserLoginStateUseCase
import io.digikraft.result.SuccessApiResult
import io.digikraft.ui.base.BaseViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    getUserLoginStateUseCase: GetUserLoginStateUseCase
) : BaseViewModel(application) {

    val logged = getUserLoginStateUseCase(Unit).map { result ->
        (result as SuccessApiResult).data
    }

    override fun onCreate() {

    }

    override fun onProgressDialogCancelled() {
        //todo: handle progressdialog cancellation by user | terminate current job
    }
}