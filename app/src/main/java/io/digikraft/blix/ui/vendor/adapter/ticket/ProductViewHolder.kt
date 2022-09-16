package io.blix.photosapp.ui.vendor.adapter.ticket

import io.digikraft.base.BaseViewHolder
import io.blix.photosapp.databinding.ItemVendorProductBinding
import io.digikraft.domain.model.ticket.TicketItem
import io.digikraft.utility.toUIDateFormat

class ProductViewHolder(
    private val binding: ItemVendorProductBinding,
    selectionList: MutableList<Int>,
) : BaseViewHolder<TicketItem, Int>(selectionList, binding.root) {

    override fun bind(position: Int, item: TicketItem) {
        binding.nameTextView.text = item.name
        binding.priceTextView.text = "${item.price} ${item.currency}"
        binding.descriptionTextView.text = item.date?.toUIDateFormat()
        binding.count = item.cartEntity?.quantity ?: 0
    }
}