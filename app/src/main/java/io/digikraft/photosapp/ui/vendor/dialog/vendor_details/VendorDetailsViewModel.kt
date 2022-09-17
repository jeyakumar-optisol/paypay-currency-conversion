package io.digikraft.photosapp.ui.vendor.dialog.vendor_details

import android.app.Application
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.digikraft.di.IoDispatcher
import io.digikraft.domain.usecase.event.GetEventOpeningHoursUseCase
import io.digikraft.result.ErrorApiResult
import io.digikraft.result.SuccessApiResult
import io.digikraft.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class VendorDetailsViewModel @Inject constructor(
    application: Application,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val getEventOpeningHoursUseCase: GetEventOpeningHoursUseCase,

    ) : BaseViewModel(application) {


    override fun onCreate() {
        //noop
    }

    fun fetchEventOpeningHours(id: String) = liveData(ioDispatcher) {
        showProgressDialog()
        when (val response = getEventOpeningHoursUseCase(id)) {
            is SuccessApiResult -> {
                emit(response)
            }
            is ErrorApiResult -> {
                errorApiResult.emit(response)
            }
        }
        hideProgressDialog()
    }
}