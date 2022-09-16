package io.digikraft.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.digikraft.data.repository.remote.DefaultVendorRepository
import io.digikraft.data.repository.remote.DefaultEventRepository
import io.digikraft.data.repository.remote.DefaultMarketplaceRepository
import io.digikraft.data.repository.remote.DefaultProfileRepository
import io.digikraft.domain.datasource.services.EventApiService
import io.digikraft.domain.datasource.IEventRepository
import io.digikraft.domain.datasource.IMarketplaceRepository
import io.digikraft.domain.datasource.IProfileRepository
import io.digikraft.domain.datasource.IVendorRepository
import io.digikraft.domain.datasource.services.MarketplaceApiService
import io.digikraft.domain.datasource.services.ProfileApiService
import io.digikraft.domain.datasource.services.VendorApiService

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    fun provideVendorRepository(vendorApiService: VendorApiService): IVendorRepository =
        DefaultVendorRepository(vendorApiService)

    @Provides
    fun provideEventRepository(eventApiService: EventApiService): IEventRepository =
        DefaultEventRepository(eventApiService)

    @Provides
    fun provideProfileRepository(profileApiService: ProfileApiService): IProfileRepository =
        DefaultProfileRepository(profileApiService)

    @Provides
    fun provideMarketplaceRepository(marketplaceApiService: MarketplaceApiService): IMarketplaceRepository =
        DefaultMarketplaceRepository(marketplaceApiService)

}