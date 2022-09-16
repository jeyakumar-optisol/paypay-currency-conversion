package io.blix.photosapp.ui.vendor

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton


interface VendorViewModelDelegate {

    val eventId: Flow<Int>
    suspend fun setEventId(id: Int)

}

@Singleton
class VendorViewModelDelegateImpl @Inject constructor() : VendorViewModelDelegate {

    private val _eventIdMutable = MutableStateFlow(21)

    override val eventId: Flow<Int>
        get() = _eventIdMutable

    override suspend fun setEventId(id: Int) {
        _eventIdMutable.value = id
    }

}