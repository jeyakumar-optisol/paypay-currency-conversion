package io.blix.photosapp.ui.menu_market

import io.digikraft.domain.model.event.item.EventItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

interface MarketViewModelDelegate {

    val buyEventList: Flow<ArrayList<EventItem>>
    suspend fun setBuyEventList(list: ArrayList<EventItem>)

    val buyEventsAfterCursor: Flow<String?>
    suspend fun setBuyEventsAfterCursor(cursor: String?)

}

@Singleton
class MarketViewModelDelegateImpl @Inject constructor() : MarketViewModelDelegate {

    private val _buyEventList = MutableStateFlow<ArrayList<EventItem>>(ArrayList())
    private val _buyEventsCursor = MutableStateFlow<String?>("")

    override val buyEventList: Flow<ArrayList<EventItem>> = _buyEventList
    override suspend fun setBuyEventList(list: ArrayList<EventItem>) {
        _buyEventList.value = list
    }

    override val buyEventsAfterCursor: Flow<String?> = _buyEventsCursor
    override suspend fun setBuyEventsAfterCursor(cursor: String?) {
        _buyEventsCursor.value = cursor
    }


}