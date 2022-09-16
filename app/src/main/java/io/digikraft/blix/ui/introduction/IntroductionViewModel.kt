package io.blix.photosapp.ui.introduction

import android.app.Application
import dagger.hilt.android.lifecycle.HiltViewModel
import io.digikraft.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class IntroductionViewModel @Inject constructor(
    application: Application
) : BaseViewModel(application) {

    override fun onCreate() {}

}