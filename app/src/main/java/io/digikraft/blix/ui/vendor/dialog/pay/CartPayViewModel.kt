package io.blix.photosapp.ui.vendor.dialog.pay

import android.app.Application
import dagger.hilt.android.lifecycle.HiltViewModel
import io.digikraft.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class CartPayViewModel @Inject constructor(application: Application) : BaseViewModel(application) {
    override fun onCreate() {

    }

}