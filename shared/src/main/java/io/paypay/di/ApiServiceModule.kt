package io.paypay.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.paypay.domain.datasource.services.EventApiService
import retrofit2.Retrofit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object ApiServiceModule {

    @Provides
    @Singleton
    fun provideEventApiService(retrofit: Retrofit): EventApiService = retrofit.create(
        EventApiService::class.java
    )
}