package io.digikraft.photosapp.ui.vendor.dialog.vendor_details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import io.digikraft.base.BaseAdapter
import io.digikraft.base.BaseViewHolder
import io.digikraft.photosapp.databinding.ItemOpeningHoursBinding
import io.digikraft.domain.model.event.opening_hours.EventOpeningHoursItem

class OpeningHoursAdapter : BaseAdapter<EventOpeningHoursItem, Int>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<EventOpeningHoursItem, Int> {
        return OpeningHoursHeader(
            ItemOpeningHoursBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), getSelections()
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<EventOpeningHoursItem, Int>, position: Int) {
        holder.bind(position, currentList[position])
    }

    override fun isSameItem(
        oldItem: EventOpeningHoursItem,
        newItem: EventOpeningHoursItem
    ): Boolean {
        return false
    }

    override fun isSameContent(
        oldItem: EventOpeningHoursItem,
        newItem: EventOpeningHoursItem,
    ): Boolean {
        return oldItem == newItem
    }

}