package io.paypay.currency.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import io.paypay.base.BaseAdapter
import io.paypay.base.BaseViewHolder
import io.paypay.currency.databinding.ItemCurrencyBinding
import io.paypay.domain.model.cart.CurrencyEntity

class CurrencyAdapter(
        private val itemListener: ItemListener? = null
) : BaseAdapter<CurrencyEntity, Int>() {

    override fun onCreateViewHolder(
            parent: ViewGroup, viewType: Int
    ): BaseViewHolder<CurrencyEntity, Int> {
        return CurrencyViewHolder(
                ItemCurrencyBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                ), getSelections()
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<CurrencyEntity, Int>, position: Int) {
        holder.bind(position, currentList[position])
        holder.itemView.setOnClickListener {
            itemListener?.onItemSelected(position, currentList[position])
        }
    }

    override fun getList(): List<CurrencyEntity> {
        return currentList
    }

    override fun isSameItem(
            oldItem: CurrencyEntity, newItem: CurrencyEntity
    ): Boolean {
        return oldItem.currency == newItem.currency
    }

    override fun isSameContent(
            oldItem: CurrencyEntity,
            newItem: CurrencyEntity,
    ): Boolean {
        return oldItem.price == newItem.price
    }

    interface ItemListener {
        fun onItemSelected(position: Int, item: CurrencyEntity) {}
    }

    companion object {
        var SelectedCurrency: CurrencyEntity? = null
        var SelectedValue: Float = 1f
    }
}