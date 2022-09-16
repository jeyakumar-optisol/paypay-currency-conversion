package io.blix.photosapp.ui.vendor.adapter.ticket

import io.digikraft.base.BaseViewHolder
import io.blix.photosapp.databinding.ItemVendorProductHeaderBinding
import io.digikraft.domain.model.ticket.TicketItem

class HeaderViewHolder(
    private val binding: ItemVendorProductHeaderBinding,
    selectionList: MutableList<Int>,
) : BaseViewHolder<TicketItem, Int>(selectionList, binding.root) {

    override fun bind(position: Int, item: TicketItem) {
        binding.nameTextView.text = item.name
    }
}