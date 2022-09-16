package io.blix.photosapp.ui.success

import android.app.Application
import dagger.hilt.android.lifecycle.HiltViewModel
import io.digikraft.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SuccessViewModel @Inject constructor(application: Application) : BaseViewModel(application) {
    override fun onCreate() {

    }

}