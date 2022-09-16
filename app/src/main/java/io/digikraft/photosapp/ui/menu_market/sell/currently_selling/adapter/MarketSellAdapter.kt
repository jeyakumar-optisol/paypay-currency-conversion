package io.digikraft.photosapp.ui.menu_market.sell.currently_selling.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.digikraft.base.BaseAdapter
import io.digikraft.base.BaseViewHolder
import io.digikraft.photosapp.databinding.ItemMarketBinding
import io.digikraft.domain.model.market.MarketItem

class MarketSellAdapter(private var listener: ItemListener) : BaseAdapter<MarketItem, Int>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<MarketItem, Int> {
        return MarketSellViewHolder(
            ItemMarketBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), getSelections()
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<MarketItem, Int>, position: Int) {
        holder.bind(position, currentList[position])
        holder.itemView.setOnClickListener {
            listener.onItemSelected(position, currentList[position])
        }
    }

    override fun isSameItem(oldItem: MarketItem, newItem: MarketItem): Boolean {
        return oldItem.name == newItem.name
    }

    override fun isSameContent(oldItem: MarketItem, newItem: MarketItem): Boolean {
        return oldItem == newItem
    }

    interface ItemListener {
        fun onItemSelection(position: Int, item: MarketItem) {}
        fun onItemSelected(position: Int, item: MarketItem) {}
        fun onOptionSelected(
            view: View? = null,
            position: Int,
            item: MarketItem,
        ) {
        }
    }
}