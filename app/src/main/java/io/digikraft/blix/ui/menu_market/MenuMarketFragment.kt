package io.blix.photosapp.ui.menu_market

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import io.blix.photosapp.databinding.FragmentMenuMarketBinding
import io.blix.photosapp.ui.menu_market.adapter.ViewPagerAdapter
import io.blix.photosapp.ui.menu_market.buy.MarketBuyFragment
import io.blix.photosapp.ui.menu_market.memorabilia.MarketMemorabiliaFragment
import io.blix.photosapp.ui.menu_market.sell.MarketSellFragment
import io.digikraft.ui.base.BaseFragment

@AndroidEntryPoint
class MenuMarketFragment : BaseFragment<FragmentMenuMarketBinding, MarketViewModel>(
    FragmentMenuMarketBinding::inflate, MarketViewModel::class.java
) {

    override fun onInit(savedInstanceState: Bundle?) {
        super.onInit(savedInstanceState)

        val list = ArrayList<Fragment>()
        list.add(MarketBuyFragment())
        list.add(MarketSellFragment())
        list.add(MarketMemorabiliaFragment())
        binding.viewPager.adapter = ViewPagerAdapter(list, childFragmentManager, lifecycle)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Buy"
                1 -> "Sell"
                else -> "Memorabilia"
            }
        }.attach()
    }

}