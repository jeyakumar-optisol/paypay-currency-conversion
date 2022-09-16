package io.digikraft.photosapp.ui.menu_tickets.active.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.digikraft.base.BaseAdapter
import io.digikraft.base.BaseViewHolder
import io.digikraft.photosapp.databinding.ItemMarketBinding
import io.digikraft.photosapp.ui.menu_market.memorabilia.adapter.MarketMemorabiliaViewHolder
import io.digikraft.domain.model.ticket.MyTicketItem

class ActiveTicketAdapter(private var listener: ItemListener) : BaseAdapter<MyTicketItem, Int>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<MyTicketItem, Int> {
        return ActiveTicketViewHolder(
            ItemMarketBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), getSelections()
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<MyTicketItem, Int>, position: Int) {
        holder.bind(position, currentList[position])
        holder.itemView.setOnClickListener {
            listener.onItemSelected(position, currentList[position])
        }
    }

    override fun isSameItem(oldItem: MyTicketItem, newItem: MyTicketItem): Boolean {
        return oldItem.name == newItem.name
    }

    override fun isSameContent(oldItem: MyTicketItem, newItem: MyTicketItem): Boolean {
        return oldItem == newItem
    }

    interface ItemListener {
        fun onItemSelection(position: Int, item: MyTicketItem) {}
        fun onItemSelected(position: Int, item: MyTicketItem) {}
        fun onOptionSelected(
            view: View? = null,
            position: Int,
            item: MyTicketItem,
        ) {
        }
    }
}