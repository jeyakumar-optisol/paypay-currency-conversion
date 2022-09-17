package io.digikraft.photosapp.ui.vendor

import androidx.test.core.app.ApplicationProvider.getApplicationContext
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import io.digikraft.photosapp.CoroutineScope
import io.digikraft.photosapp.LiveDataTestUtil
import io.digikraft.photosapp.base.BaseTestClass
import io.digikraft.domain.datasource.IEventRepository
import io.digikraft.domain.datasource.IPreferenceStorage
import io.digikraft.domain.datasource.IRoomDatabaseRepository
import io.digikraft.domain.usecase.cart.GetEventCartsUseCase
import io.digikraft.domain.usecase.event.GetEventTicketsUseCase
import io.digikraft.utility.getCurrentDateTimeAPI
import kotlinx.coroutines.launch
import org.junit.Test
import org.robolectric.annotation.Config
import javax.inject.Inject

@Config(application = HiltTestApplication::class)
@HiltAndroidTest
class VendorViewModelTest : BaseTestClass() {

    private lateinit var viewModel: VendorViewModel

    @Inject
    lateinit var eventRepository: IEventRepository

    @Inject
    lateinit var preferenceStorage: IPreferenceStorage

    @Inject
    lateinit var roomDatabaseRepository: IRoomDatabaseRepository

    @Inject
    lateinit var vendorViewModelDelegate: VendorViewModelDelegate

    @Test
    fun fetchEventTickets() {
        viewModel = prepareViewModel()

        val job = coroutineRule.CoroutineScope().launch {
            val result = LiveDataTestUtil.getValue(viewModel.fetchEventTickets(20, "01-01-2021", getCurrentDateTimeAPI()))
            assert(result?.size != 0)
        }
        job.cancel()
    }

    private fun prepareViewModel(): VendorViewModel {
        val getEventCartsUseCase = GetEventCartsUseCase(roomDatabaseRepository)
        val getEventTicketsUseCase = GetEventTicketsUseCase(coroutineRule.testDispatcher, eventRepository, getEventCartsUseCase, preferenceStorage)

        return VendorViewModel(
            getApplicationContext(),
            coroutineRule.testDispatcher,
            getEventTicketsUseCase,
            getEventCartsUseCase,
            vendorViewModelDelegate
        )
    }

}