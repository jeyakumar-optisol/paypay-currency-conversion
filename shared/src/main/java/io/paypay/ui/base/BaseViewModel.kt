package io.paypay.ui.base

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.paypay.base.AbstractViewModel
import io.paypay.result.ErrorApiResult
import io.paypay.domain.model.LoaderProperties
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import org.jetbrains.anko.runOnUiThread

abstract class BaseViewModel(application: Application) : AbstractViewModel(application) {

    val errorApiResult = MutableSharedFlow<ErrorApiResult<Any>>()
    val liveDataProgressBar = MutableLiveData<LoaderProperties>()

    open fun showProgressDialog(
        message: String = "loading..", progress: Int = -1, cancelable: Boolean = false
    ) {
        liveDataProgressBar.postValue(
            LoaderProperties(
                true, message, progress, cancelable
            )
        )
    }

    open fun hideProgressDialog() {
        liveDataProgressBar.postValue(LoaderProperties(false))
    }

}