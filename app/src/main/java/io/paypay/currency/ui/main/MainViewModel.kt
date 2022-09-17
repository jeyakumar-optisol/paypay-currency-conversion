package io.paypay.currency.ui.main

import android.app.Application
import dagger.hilt.android.lifecycle.HiltViewModel
import io.paypay.domain.datasource.IEventRepository
import io.paypay.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    eventRepository: IEventRepository
) : BaseViewModel(application) {

    /*val logged = getUserLoginStateUseCase(Unit).map { result ->
        (result as SuccessApiResult).data
    }*/

    override fun onCreate() {

    }

    override fun onProgressDialogCancelled() {
        //todo: handle progressdialog cancellation by user | terminate current job
    }
}