package io.blix.photosapp.ui.menu_market.memorabilia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.blix.photosapp.R
import io.blix.photosapp.databinding.FragmentMarketMemorabiliaBinding
import io.blix.photosapp.ui.menu_market.MarketViewModel
import io.blix.photosapp.ui.menu_market.buy.adapter.MarketBuyAdapter
import io.blix.photosapp.ui.menu_market.memorabilia.adapter.MarketMemorabiliaAdapter
import io.digikraft.domain.model.market.MarketItem
import io.digikraft.ui.base.BaseFragment
import io.digikraft.utility.fragment.goto

@AndroidEntryPoint
class MarketMemorabiliaFragment : BaseFragment<FragmentMarketMemorabiliaBinding, MarketViewModel>(
    FragmentMarketMemorabiliaBinding::inflate, MarketViewModel::class.java
) {

    override fun onInit(savedInstanceState: Bundle?) {
        super.onInit(savedInstanceState)

        initUi()
    }

    private fun initUi() {
        val list = ArrayList<MarketItem>()
        for (i in 0 until 10) {
            list.add(MarketItem())
        }
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = MarketMemorabiliaAdapter(itemListener).apply {
                submitList(list)
            }
        }
    }

    private val itemListener = object : MarketMemorabiliaAdapter.ItemListener {
        override fun onItemSelected(position: Int, item: MarketItem) {
            goto(R.id.vendorFragment)
        }
    }
}