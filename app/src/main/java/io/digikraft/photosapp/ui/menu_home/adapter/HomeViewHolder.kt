package io.digikraft.photosapp.ui.menu_home.adapter

import io.digikraft.base.BaseViewHolder
import io.digikraft.photosapp.databinding.ItemHomeBinding
import io.digikraft.domain.model.event.item.EventItem
import io.digikraft.utility.formatDateHomeItems
import io.digikraft.utility.view.loadImage

class HomeViewHolder(
    private val binding: ItemHomeBinding,
    selectionList: MutableList<Int>,
) : BaseViewHolder<EventItem, Int>(selectionList, binding.root) {

    override fun bind(position: Int, item: EventItem) {
        binding.image.loadImage(item.imageUrl)
        binding.nameTextView.text = item.name
        binding.datePriceTextView.text = "${item.createdAt?.formatDateHomeItems()}" /*• ${item.price}*/
    }
}