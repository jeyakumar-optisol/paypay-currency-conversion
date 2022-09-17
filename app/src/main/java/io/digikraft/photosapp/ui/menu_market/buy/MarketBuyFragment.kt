package io.digikraft.photosapp.ui.menu_market.buy

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import io.digikraft.photosapp.R
import io.digikraft.photosapp.databinding.FragmentMarketBuyBinding
import io.digikraft.photosapp.ui.menu_market.MarketViewModel
import io.digikraft.photosapp.ui.menu_market.buy.adapter.MarketBuyAdapter
import io.digikraft.domain.model.event.item.EventItem
import io.digikraft.ui.base.BaseFragment
import io.digikraft.utility.fragment.goto
import io.digikraft.utility.fragment.launchAndRepeatWithViewLifecycle
import io.digikraft.utility.view.EndlessRecyclerViewScrollListener
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MarketBuyFragment : BaseFragment<FragmentMarketBuyBinding, MarketViewModel>(
    FragmentMarketBuyBinding::inflate, MarketViewModel::class.java
) {

    override fun onInit(savedInstanceState: Bundle?) {
        super.onInit(savedInstanceState)
        initUi()
        initObserver()
    }

    private fun initUi() = with(binding) {
        recyclerView.apply {
            addOnScrollListener(object : EndlessRecyclerViewScrollListener(layoutManager as GridLayoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                    fetchMarketplaceEvents()
                }
            })
            adapter = MarketBuyAdapter(itemListener)
        }
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            clearData()
        }
    }


    private fun initObserver() {
        launchAndRepeatWithViewLifecycle {
            viewModel.buyEventList.collect {
                (binding.recyclerView.adapter as MarketBuyAdapter).submitList(it)
            }
        }
        fetchMarketplaceEvents()
    }

    private val itemListener = object : MarketBuyAdapter.ItemListener {
        override fun onItemSelected(position: Int, item: EventItem) {
            goto(R.id.vendorFragment)
        }
    }

    private fun fetchMarketplaceEvents() {
        launchAndRepeatWithViewLifecycle {
            if (viewModel.buyEventList.first().isEmpty()) {
                viewModel.fetchEventList()
            }
        }
    }

    private fun clearData() {
        viewLifecycleOwner.lifecycleScope.launch {
            (binding.recyclerView.adapter as MarketBuyAdapter).submitList(null)
            viewModel.clearBuyEvents()
            fetchMarketplaceEvents()
        }
    }

}