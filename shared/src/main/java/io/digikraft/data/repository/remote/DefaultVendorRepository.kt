package io.digikraft.data.repository.remote

import io.digikraft.domain.datasource.IVendorRepository
import io.digikraft.domain.datasource.services.VendorApiService

class DefaultVendorRepository constructor(private val vendorApiService: VendorApiService) :
    IVendorRepository {

    /*override suspend fun fetchVendorList(): Result<ArrayList<VendorProductEntity>> =
        vendorApiService.vendorList()*/

}

