package io.digikraft.photosapp.ui.menu_home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.digikraft.base.BaseAdapter
import io.digikraft.base.BaseViewHolder
import io.digikraft.photosapp.databinding.ItemHomeBinding
import io.digikraft.domain.model.event.item.EventItem
import io.digikraft.domain.model.home.HomeItem

class HomeStaffAdapter(private var listener: ItemListener) : BaseAdapter<EventItem, Int>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<EventItem, Int> {
        return HomeViewHolder(
            ItemHomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), getSelections()
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<EventItem, Int>, position: Int) {
        holder.bind(position, currentList[position])
        holder.itemView.setOnClickListener {
            listener.onItemSelected(position, currentList[position])
        }
    }

    override fun isSameItem(oldItem: EventItem, newItem: EventItem): Boolean {
        return oldItem.name == newItem.name
    }

    override fun isSameContent(oldItem: EventItem, newItem: EventItem): Boolean {
        return oldItem == newItem
    }

    interface ItemListener {
        fun onItemSelection(position: Int, item: EventItem) {}
        fun onItemSelected(position: Int, item: EventItem) {}
    }
}