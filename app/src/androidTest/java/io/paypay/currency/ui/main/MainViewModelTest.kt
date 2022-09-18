package io.paypay.currency.ui.main

import androidx.test.core.app.ApplicationProvider.getApplicationContext
import dagger.hilt.android.testing.HiltAndroidTest
import io.paypay.domain.datasource.IEventRepository
import io.paypay.domain.datasource.IPreferenceStorage
import io.paypay.domain.usecase.event.FetchLatestCurrencyUseCase
import io.paypay.currency.base.BaseTestClass
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class MainViewModelTest : BaseTestClass() {

    @Inject
    lateinit var eventRepository: IEventRepository

    @Inject
    lateinit var preferenceStorage: IPreferenceStorage


    @Test
    fun fetchEventsList() = runTest {
        val viewModel = prepareViewModel()
        //viewModel.fetchEventsList()
        val currentJob = launch(coroutineRule.testDispatcher) {
            /*viewModel.eventList.collectLatest {
                *//*if (it.isNotEmpty()) {
                    assertTrue(it.size > 0)
                }*//*
            }*/
        }
        currentJob.cancel()
    }

    private fun prepareViewModel(): MainViewModel {
        val getEventsListUseCase = FetchLatestCurrencyUseCase(
            coroutineRule.testDispatcher, eventRepository
        )
        return MainViewModel(getApplicationContext(), eventRepository)
    }
}