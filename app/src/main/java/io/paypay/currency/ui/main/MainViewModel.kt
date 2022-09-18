package io.paypay.currency.ui.main

import android.app.Application
import dagger.hilt.android.lifecycle.HiltViewModel
import io.paypay.di.IoDispatcher
import io.paypay.domain.dao.CurrencyDao
import io.paypay.domain.datasource.IPreferenceStorage
import io.paypay.domain.usecase.event.FetchLatestCurrencyUseCase
import io.paypay.result.ErrorApiResult
import io.paypay.result.SuccessApiResult
import io.paypay.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private var latestCurrencyUseCase: FetchLatestCurrencyUseCase,
    private var iPreferenceStorage: IPreferenceStorage,
    private var currencyDao: CurrencyDao
) : BaseViewModel(application) {

    val lastUpdate = iPreferenceStorage.lastUpdate
    val currencyLiveData = currencyDao.currencyListLiveData

    override fun onCreate() {
        //todo:
    }

    fun fetchCurrencyList() = channelFlow {
        val updatedTime = lastUpdate.first().toLongOrNull()
        if (updatedTime != null) {
            val occurredMinutes =
                TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis() - updatedTime)
            if (occurredMinutes < 30) {
                channel.send(Unit)
                return@channelFlow
            }
        }
        showProgressDialog()
        latestCurrencyUseCase.invoke(Unit).collectLatest { result ->
            when (result) {
                is SuccessApiResult -> {
                    //fixme: handle result
                    this.channel.send(Unit)
                }
                is ErrorApiResult -> {
                    errorApiResult.emit(result)
                }
            }
            hideProgressDialog()
        }
    }

    override fun onProgressDialogCancelled() {
        //todo: handle progressdialog cancellation by user | terminate current job
    }
}