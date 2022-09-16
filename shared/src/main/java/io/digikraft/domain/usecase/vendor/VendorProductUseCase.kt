/*
package io.digikraft.domain.usecase.vendor

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import io.digikraft.data.repository.local.RoomDatabaseRepository
import io.digikraft.domain.model.vendor.VendorProductEntity
import io.digikraft.base.FlowUseCase
import io.digikraft.domain.datasource.IVendorRepository
import io.digikraft.result.NetworkResult
import io.digikraft.result.SuccessApiResult
import io.digikraft.shared.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.Reader
import java.lang.reflect.Type
import javax.inject.Inject

class VendorProductUseCase @Inject constructor(
    private val roomDatabaseRepository: RoomDatabaseRepository,
    @ApplicationContext private val context: Context
) : FlowUseCase<Unit, ArrayList<VendorProductEntity>>() {

    val vendorProductList = MutableLiveData<ArrayList<VendorProductEntity>>()

    override fun performAction(parameters: Unit): Flow<NetworkResult<ArrayList<VendorProductEntity>>> {

        val raw: InputStream = context.resources.openRawResource(R.raw.vendor_mock)
        val rd: Reader = BufferedReader(InputStreamReader(raw))

        val listType: Type = object : TypeToken<ArrayList<VendorProductEntity>>() {}.type

        val list: ArrayList<VendorProductEntity> = Gson().fromJson(rd, listType)
        list.add(VendorProductEntity().apply {
            name = "Popular"
            type = 0
        })

//        val response = getResult { vendorRepository.fetchVendorList() }


        return flow { emit(SuccessApiResult(list)) }
    }

}*/
