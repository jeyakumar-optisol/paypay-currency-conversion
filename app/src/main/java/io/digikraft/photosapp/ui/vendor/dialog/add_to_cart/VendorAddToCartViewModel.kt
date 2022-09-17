package io.digikraft.photosapp.ui.vendor.dialog.add_to_cart

import android.app.Application
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.digikraft.di.IoDispatcher
import io.digikraft.domain.dao.CartDao
import io.digikraft.domain.model.cart.CartEntity
import io.digikraft.domain.model.ticket.TicketItem
import io.digikraft.domain.usecase.cart.AddCartTicketUseCase
import io.digikraft.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class VendorAddToCartViewModel @Inject constructor(
    application: Application,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val addCartTicketUseCase: AddCartTicketUseCase,
    private val cartDao: CartDao
) : BaseViewModel(application) {

    override fun onCreate() {
        //noop
    }

    fun getItemCartCount(function: (count: Int) -> Unit) {
        function(0)
    }


    fun updateCartCount(
        item: TicketItem,
        count: Int
    ) = liveData(ioDispatcher) {
        addCartTicketUseCase(Pair(item, count)).collect {
            emit(it)
        }
    }

    fun updateCartCount(
        item: TicketItem,
        count: Int,
        callback: () -> Unit
    ) {
        cartDao.insert((item.cartEntity ?: CartEntity().apply {
            this.id = item.id
            this.eventId = item.eventId
        }).apply {
            this.quantity = count
        })
        callback()
    }

}