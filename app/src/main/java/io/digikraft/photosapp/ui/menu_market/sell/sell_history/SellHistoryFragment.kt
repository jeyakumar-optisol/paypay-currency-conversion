package io.digikraft.photosapp.ui.menu_market.sell.sell_history

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.digikraft.photosapp.R
import io.digikraft.photosapp.databinding.FragmentSellHistoryBinding
import io.digikraft.photosapp.ui.menu_market.MarketViewModel
import io.digikraft.photosapp.ui.menu_market.sell.currently_selling.adapter.MarketSellAdapter
import io.digikraft.photosapp.ui.menu_market.sell.sell_history.adapter.SellHistoryAdapter
import io.digikraft.domain.model.market.MarketItem
import io.digikraft.domain.model.market.MarketModel
import io.digikraft.ui.base.BaseFragment
import io.digikraft.utility.fragment.goto

@AndroidEntryPoint
class SellHistoryFragment : BaseFragment<FragmentSellHistoryBinding, MarketViewModel>(
    FragmentSellHistoryBinding::inflate, MarketViewModel::class.java
) {

    override fun onInit(savedInstanceState: Bundle?) {
        super.onInit(savedInstanceState)
        val list = ArrayList<MarketItem>()
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = SellHistoryAdapter(itemListener).apply {
                submitList(list)
            }
        }
        binding.marketModel = MarketModel(list)
    }

    private val itemListener = object : SellHistoryAdapter.ItemListener {
        override fun onItemSelected(position: Int, item: MarketItem) {
            goto(R.id.vendorFragment)
        }
    }

}