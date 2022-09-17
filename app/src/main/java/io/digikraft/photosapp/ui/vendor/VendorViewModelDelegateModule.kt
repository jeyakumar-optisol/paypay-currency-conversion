package io.digikraft.photosapp.ui.vendor

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object VendorViewModelDelegateModule {

    @Provides
    @Singleton
    fun provideVendorViewModelDelegate(): VendorViewModelDelegate = VendorViewModelDelegateImpl()

}
