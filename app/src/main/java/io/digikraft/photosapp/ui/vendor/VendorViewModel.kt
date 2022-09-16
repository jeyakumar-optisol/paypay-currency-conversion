package io.digikraft.photosapp.ui.vendor

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.digikraft.di.IoDispatcher
import io.digikraft.domain.dao.CartDao
import io.digikraft.domain.model.cart.CartEntity
import io.digikraft.domain.model.ticket.TicketItem
import io.digikraft.domain.usecase.cart.GetEventCartsUseCase
import io.digikraft.domain.usecase.event.GetEventTicketsUseCase
import io.digikraft.result.ErrorApiResult
import io.digikraft.result.SuccessApiResult
import io.digikraft.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VendorViewModel @Inject constructor(
    application: Application,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val getEventTicketsUseCase: GetEventTicketsUseCase,
    private val getEventCartsUseCase: GetEventCartsUseCase,
    /*private val vendorProductUseCase: VendorProductUseCase,*/
    vendorViewModelDelegate: VendorViewModelDelegate
) : BaseViewModel(application), VendorViewModelDelegate by vendorViewModelDelegate {

    override fun onCreate() {
        //noop
    }

    fun fetchEventTickets(
        eventId: Int,
        fromDate: String?,
        toDate: String?
    ) = liveData(ioDispatcher) {
        showProgressDialog()
        when (val response = getEventTicketsUseCase(Triple(eventId, fromDate, toDate))) {
            is SuccessApiResult -> {
                emit(response.data.data)
            }
            is ErrorApiResult -> {
                errorApiResult.emit(response)
            }
        }
        hideProgressDialog()
    }

//    fun getCartCacheHookup(selectedEventId: Int): LiveData<List<CartEntity>> =
//        cartDao.cartEventLive(selectedEventId)

    suspend fun getCartCacheHookup(selectedEventId: Int): Flow<List<CartEntity>> =
        getEventCartsUseCase.invoke(selectedEventId)

}