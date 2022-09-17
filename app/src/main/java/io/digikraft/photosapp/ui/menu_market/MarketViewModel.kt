package io.digikraft.photosapp.ui.menu_market

import android.app.Application
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.digikraft.data.repository.remote.DefaultMarketplaceRepository
import io.digikraft.di.IoDispatcher
import io.digikraft.domain.datasource.IMarketplaceRepository
import io.digikraft.domain.model.event.item.EventItem
import io.digikraft.domain.usecase.marketplace.GetMarketplaceEventsUseCase
import io.digikraft.result.ErrorApiResult
import io.digikraft.result.SuccessApiResult
import io.digikraft.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

@HiltViewModel
class MarketViewModel @Inject constructor(
    application: Application,
    private val getMarketplaceEventsUseCase: GetMarketplaceEventsUseCase,
    marketViewModelDelegate: MarketViewModelDelegate
) : BaseViewModel(application), MarketViewModelDelegate by marketViewModelDelegate {

    override fun onCreate() {

    }

    fun fetchEventList() {
        viewModelScope.launch {
            buyEventsAfterCursor.first()?.let { afterCursor ->
                showProgressDialog()
                getMarketplaceEventsUseCase(afterCursor).collectLatest { result ->
                    hideProgressDialog()
                    when (result) {
                        is SuccessApiResult -> {
                            setBuyEventsAfterCursor(result.data.cursor?.afterCursor)

                            val list = ArrayList<EventItem>()
                            list.addAll(buyEventList.first())
                            list.addAll(result.data.data ?: ArrayList())
                            setBuyEventList(list)
                        }
                        is ErrorApiResult -> {
                            errorApiResult.emit(result)
                        }
                    }
                }
            }
        }
    }

    fun clearBuyEvents() {
        viewModelScope.launch {
            setBuyEventsAfterCursor("")
            setBuyEventList(ArrayList())
        }
    }


}