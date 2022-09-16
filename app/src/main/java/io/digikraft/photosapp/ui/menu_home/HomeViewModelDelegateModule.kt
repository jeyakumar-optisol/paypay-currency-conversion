package io.digikraft.photosapp.ui.menu_home

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HomeViewModelDelegateModule {

    @Provides
    @Singleton
    fun provideHomeViewModelDelegate(): HomeViewModelDelegate = HomeViewModelDelegateImpl()

}
