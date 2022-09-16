package io.blix.photosapp.ui.menu_market.sell.sell_history.adapter

import io.digikraft.base.BaseViewHolder
import io.blix.photosapp.databinding.ItemMarketBinding
import io.digikraft.domain.model.market.MarketItem

class SellHistoryViewHolder(
    private val binding: ItemMarketBinding,
    selectionList: MutableList<Int>,
) : BaseViewHolder<MarketItem, Int>(selectionList, binding.root) {

    override fun bind(position: Int, item: MarketItem) {
        binding.nameTextView.text = item.name
        binding.datePriceTextView.text = "${item.date} • ${item.price}"
    }
}