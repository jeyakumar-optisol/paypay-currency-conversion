package io.digikraft.photosapp.ui.menu_profile

import android.app.Application
import dagger.hilt.android.lifecycle.HiltViewModel
import io.digikraft.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(application: Application) : BaseViewModel(application) {

    override fun onCreate() {

    }
}