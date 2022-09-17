package io.digikraft.photosapp.ui.menu_tickets.active.adapter

import io.digikraft.base.BaseViewHolder
import io.digikraft.photosapp.databinding.ItemMarketBinding
import io.digikraft.domain.model.market.MarketItem
import io.digikraft.domain.model.ticket.MyTicketItem

class ActiveTicketViewHolder(
    private val binding: ItemMarketBinding,
    selectionList: MutableList<Int>,
) : BaseViewHolder<MyTicketItem, Int>(selectionList, binding.root) {

    override fun bind(position: Int, item: MyTicketItem) {
        binding.nameTextView.text = item.name
        binding.datePriceTextView.text = "${item.date} • ${item.price}"
    }
}