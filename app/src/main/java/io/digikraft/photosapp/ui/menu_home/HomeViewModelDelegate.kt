package io.digikraft.photosapp.ui.menu_home

import io.digikraft.domain.model.event.item.EventItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

interface HomeViewModelDelegate {

    val eventList: Flow<ArrayList<EventItem>>
    suspend fun setEventList(list: ArrayList<EventItem>)

    val eventsAfterCursor: Flow<String?>
    suspend fun setEventsAfterCursor(cursor: String?)

}

@Singleton
class HomeViewModelDelegateImpl @Inject constructor() : HomeViewModelDelegate {

    private val _eventList = MutableStateFlow<ArrayList<EventItem>>(ArrayList())
    private val _eventsCursor = MutableStateFlow<String?>("")

    override val eventList: Flow<ArrayList<EventItem>> = _eventList
    override suspend fun setEventList(list: ArrayList<EventItem>) {
        _eventList.value = list
    }

    override val eventsAfterCursor: Flow<String?> = _eventsCursor
    override suspend fun setEventsAfterCursor(cursor: String?) {
        _eventsCursor.value = cursor
    }


}