package io.digikraft.photosapp.ui.menu_home

import android.app.Application
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.digikraft.di.IoDispatcher
import io.digikraft.domain.datasource.IEventRepository
import io.digikraft.domain.model.event.item.EventItem
import io.digikraft.domain.usecase.event.GetEventsListUseCase
import io.digikraft.getResult
import io.digikraft.result.ErrorApiResult
import io.digikraft.result.SuccessApiResult
import io.digikraft.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val getEventsListUseCase: GetEventsListUseCase,
    homeViewModelDelegate: HomeViewModelDelegate
) : BaseViewModel(application), HomeViewModelDelegate by homeViewModelDelegate {

    override fun onCreate() {
        //todo:
    }

    fun fetchEventsList() {
        viewModelScope.launch {
            val cursor = eventsAfterCursor.first()
            cursor?.let {
                showProgressDialog()
                getEventsListUseCase.invoke(it).collectLatest { result ->
                    when (result) {
                        is SuccessApiResult -> {
                            setEventsAfterCursor(result.data.cursor?.afterCursor)

                            val list = ArrayList<EventItem>()
                            list.addAll(eventList.first())
                            list.addAll(result.data.data ?: ArrayList())
                            setEventList(list)
                        }
                        is ErrorApiResult -> {
                            errorApiResult.emit(result)
                        }
                    }
                }
                hideProgressDialog()
            }
        }
    }

}