package io.blix.photosapp.ui.vendor.dialog.vendor_details.adapter

import io.digikraft.base.BaseViewHolder
import io.blix.photosapp.databinding.ItemOpeningHoursBinding
import io.digikraft.domain.model.event.opening_hours.EventOpeningHoursItem
import io.digikraft.utility.toUIDateFormat

class OpeningHoursHeader(
    private val binding: ItemOpeningHoursBinding,
    selectionList: MutableList<Int>,
) : BaseViewHolder<EventOpeningHoursItem, Int>(selectionList, binding.root) {

    override fun bind(position: Int, item: EventOpeningHoursItem) {
        binding.weekdayTextView.text = item.weekday ?: item.date?.toUIDateFormat()
        binding.timeTextView.text = "${item.timeFrom} ${item.timeTo}"
    }
}