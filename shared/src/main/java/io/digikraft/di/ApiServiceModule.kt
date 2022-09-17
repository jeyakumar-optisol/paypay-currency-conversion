package io.digikraft.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.digikraft.domain.datasource.services.EventApiService
import io.digikraft.domain.datasource.services.MarketplaceApiService
import io.digikraft.domain.datasource.services.ProfileApiService
import retrofit2.Retrofit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object ApiServiceModule {

    /*@Provides
    fun provideRestRepository(retrofit: Retrofit): IRestDataService {
        return RestDataRepository(retrofit.create(IRestDataService::class.java))
    }*/

    @Provides
    @Singleton
    fun provideEventApiService(retrofit: Retrofit): EventApiService = retrofit.create(
        EventApiService::class.java
    )

    @Provides
    @Singleton
    fun provideProfileApiService(retrofit: Retrofit): ProfileApiService = retrofit.create(
        ProfileApiService::class.java
    )

    @Provides
    @Singleton
    fun provideMarketplaceApiService(retrofit: Retrofit): MarketplaceApiService = retrofit.create(
        MarketplaceApiService::class.java
    )


}