package io.paypay.currency.ui.main.adapter

import io.paypay.base.BaseViewHolder
import io.paypay.currency.databinding.ItemCurrencyBinding
import io.paypay.domain.model.cart.CurrencyEntity

class CurrencyViewHolder(
        private val binding: ItemCurrencyBinding,
        selectionList: MutableList<Int>,
) : BaseViewHolder<CurrencyEntity, Int>(selectionList, binding.root) {

    override fun bind(position: Int, item: CurrencyEntity) {
        binding.currency = item.currency
        binding.price = (CurrencyAdapter.SelectedValue ?: 0f)
                        .times(item.price?.toFloatOrNull() ?: 0f)
                        .toString()
    }
}