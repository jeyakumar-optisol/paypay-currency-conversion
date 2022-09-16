package io.blix.photosapp.ui.menu_market

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object MarketViewModelDelegateModule {

    @Provides
    @Singleton
    fun provideHomeViewModelDelegate(): MarketViewModelDelegate = MarketViewModelDelegateImpl()

}
