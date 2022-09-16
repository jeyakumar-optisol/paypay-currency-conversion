package io.digikraft.domain.model.ticket

import io.digikraft.domain.model.cart.CartEntity

data class TicketItem(
    val availableForSale: Int? = null,
    val createdAt: String? = null,
    val currency: String? = null,
    val date: String? = null,
    val deletedAt: String? = null,
    val eventId: Int = 0,
    val filterAge: String? = null,
    val filterType: String? = null,
    val id: Int,
    val imageUrl: String? = null,
    val metadataUrl: String? = null,
    val name: String? = null,
    val price: String? = null,
    val quantity: Int = 0,
    val tokenId: String? = null,
    val type: String? = null,
    val updatedAt: String? = null,
    val view_holder_type: Int? = null
) {
    @Transient
    var cartEntity: CartEntity? = null
}