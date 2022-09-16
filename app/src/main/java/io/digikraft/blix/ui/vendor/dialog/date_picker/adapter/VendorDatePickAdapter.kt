package io.blix.photosapp.ui.vendor.dialog.date_picker.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import io.digikraft.base.BaseAdapter
import io.digikraft.base.BaseViewHolder
import io.blix.photosapp.databinding.ItemDatesPickerBinding

class VendorDatePickAdapter(private val listener: (Int) -> Unit) : BaseAdapter<String, Int>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<String, Int> {
        return VendorDatePickViewHolder(ItemDatesPickerBinding.inflate(LayoutInflater.from(parent.context), parent, false), getSelections())
    }

    override fun onBindViewHolder(holder: BaseViewHolder<String, Int>, position: Int) {
        holder.bind(position, currentList[position])
        holder.itemView.setOnClickListener { listener.invoke(position) }
    }

    override fun isSameItem(
        oldItem: String,
        newItem: String
    ): Boolean {
        return oldItem == newItem
    }

    override fun isSameContent(
        oldItem: String,
        newItem: String,
    ): Boolean {
        return oldItem == newItem
    }

}