package io.blix.photosapp.ui.vendor.adapter.ticket

import android.view.LayoutInflater
import android.view.ViewGroup
import io.digikraft.base.BaseAdapter
import io.digikraft.base.BaseViewHolder
import io.blix.photosapp.databinding.ItemVendorProductBinding
import io.blix.photosapp.databinding.ItemVendorProductHeaderBinding
import io.digikraft.domain.model.ticket.TicketItem

class VendorTicketAdapter(
    private val itemListener: ItemListener? = null
) : BaseAdapter<TicketItem, Int>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<TicketItem, Int> {
        return if (viewType == 0) {
            HeaderViewHolder(
                ItemVendorProductHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ), getSelections()
            )
        } else {
            ProductViewHolder(
                ItemVendorProductBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ), getSelections()
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return currentList[position].view_holder_type ?: 1
    }

    override fun onBindViewHolder(holder: BaseViewHolder<TicketItem, Int>, position: Int) {
        holder.bind(position, currentList[position])
        if (holder is ProductViewHolder) {
            holder.itemView.setOnClickListener {
                itemListener?.onItemSelected(position, currentList[position])
            }
        }
    }

    override fun getList(): List<TicketItem>{
        return currentList
    }

    override fun isSameItem(
        oldItem: TicketItem,
        newItem: TicketItem
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun isSameContent(
        oldItem: TicketItem,
        newItem: TicketItem,
    ): Boolean {
        return false
    }

    interface ItemListener {
        fun onItemSelected(position: Int, item: TicketItem) {}
    }
}