package io.blix.photosapp.ui.vendor.dialog.date_picker.adapter

import io.digikraft.base.BaseViewHolder
import io.blix.photosapp.databinding.ItemDatesPickerBinding

class VendorDatePickViewHolder(
    private val binding: ItemDatesPickerBinding,
    selectionList: MutableList<Int>,
) : BaseViewHolder<String, Int>(selectionList, binding.root) {

    override fun bind(position: Int, item: String) {
        binding.dateTextView.text = item
    }
}