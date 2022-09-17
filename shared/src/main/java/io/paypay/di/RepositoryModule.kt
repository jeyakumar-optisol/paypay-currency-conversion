package io.paypay.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.paypay.data.repository.remote.DefaultEventRepository
import io.paypay.domain.datasource.IEventRepository
import io.paypay.domain.datasource.services.EventApiService

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    fun provideEventRepository(eventApiService: EventApiService): IEventRepository =
        DefaultEventRepository(eventApiService)

}