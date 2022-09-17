package io.digikraft.photosapp.ui.menu_home

import android.util.Log
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import dagger.hilt.android.testing.HiltAndroidTest
import io.digikraft.photosapp.CoroutineScope
import io.digikraft.photosapp.MainCoroutineRule
import io.digikraft.photosapp.base.BaseTestClass
import io.digikraft.domain.datasource.IEventRepository
import io.digikraft.domain.datasource.IPreferenceStorage
import io.digikraft.domain.usecase.event.GetEventsListUseCase
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class HomeViewModelTest : BaseTestClass() {

    @Inject
    lateinit var eventRepository: IEventRepository

    @Inject
    lateinit var homeViewModelDelegate: HomeViewModelDelegate

    @Inject
    lateinit var preferenceStorage: IPreferenceStorage


    @Test
    fun fetchEventsList() = runTest {
        val viewModel = prepareViewModel()
        viewModel.fetchEventsList()
        val currentJob = launch(coroutineRule.testDispatcher) {
            viewModel.eventList.collectLatest {
                if (it.isNotEmpty()) {
                    assertTrue(it.size > 0)
                }
            }
        }
        currentJob.cancel()
    }

    private fun prepareViewModel(): HomeViewModel {
        val getEventsListUseCase = GetEventsListUseCase(
            coroutineRule.testDispatcher, eventRepository, preferenceStorage)
        return HomeViewModel(getApplicationContext(), coroutineRule.testDispatcher, getEventsListUseCase, homeViewModelDelegate)
    }
}