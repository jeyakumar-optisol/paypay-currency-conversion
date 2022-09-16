package io.blix.photosapp.ui.menu_market.sell.currently_selling

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.blix.photosapp.R
import io.blix.photosapp.databinding.FragmentCurrentlySellingBinding
import io.blix.photosapp.ui.menu_market.MarketViewModel
import io.blix.photosapp.ui.menu_market.sell.currently_selling.adapter.MarketSellAdapter
import io.digikraft.domain.model.market.MarketItem
import io.digikraft.domain.model.market.MarketModel
import io.digikraft.ui.base.BaseFragment
import io.digikraft.utility.fragment.goto

@AndroidEntryPoint
class CurrentlySellingFragment : BaseFragment<FragmentCurrentlySellingBinding, MarketViewModel>(
    FragmentCurrentlySellingBinding::inflate, MarketViewModel::class.java
) {

    override fun onInit(savedInstanceState: Bundle?) {
        super.onInit(savedInstanceState)

        val list = ArrayList<MarketItem>()
        for (i in 0 until 10) {
            list.add(MarketItem())
        }
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = MarketSellAdapter(itemListener).apply {
                submitList(list)
            }
        }
        binding.marketModel = MarketModel(list)
    }


    private val itemListener = object : MarketSellAdapter.ItemListener {
        override fun onItemSelected(position: Int, item: MarketItem) {
            goto(R.id.vendorFragment)
        }
    }

}