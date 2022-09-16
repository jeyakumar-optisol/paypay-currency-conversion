package io.digikraft.domain.model.event.create

data class CreateEventResponse(
    val address: String,
    val description: String,
    val googleMapInfo: String,
    val imageUrl: String,
    val latitude: String,
    val longitude: String,
    val name: String,
    val siteUrl: String,
    val typeId: Int
)