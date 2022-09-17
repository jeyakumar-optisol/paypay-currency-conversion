package io.digikraft.photosapp.ui.menu_market.sell

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import dagger.hilt.android.AndroidEntryPoint
import io.digikraft.photosapp.databinding.FragmentMarketSellBinding
import io.digikraft.photosapp.ui.menu_market.MarketViewModel
import io.digikraft.photosapp.ui.menu_market.adapter.ViewPagerAdapter
import io.digikraft.photosapp.ui.menu_market.sell.currently_selling.CurrentlySellingFragment
import io.digikraft.photosapp.ui.menu_market.sell.sell_history.SellHistoryFragment
import io.digikraft.ui.base.BaseFragment

@AndroidEntryPoint
class MarketSellFragment : BaseFragment<FragmentMarketSellBinding, MarketViewModel>(
    FragmentMarketSellBinding::inflate, MarketViewModel::class.java
) {

    override fun onInit(savedInstanceState: Bundle?) {
        super.onInit(savedInstanceState)

        initUi()
    }

    private fun initUi() {
        val list = ArrayList<Fragment>()
        list.add(CurrentlySellingFragment())
        list.add(SellHistoryFragment())
        binding.viewPager.adapter = ViewPagerAdapter(list, childFragmentManager, lifecycle)
        binding.viewPager.isUserInputEnabled = false
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                selectPage(position)
            }
        })
        binding.tab1.setOnClickListener { selectPage(0) }
        binding.tab2.setOnClickListener { selectPage(1) }
    }

    private fun selectPage(position: Int) {
        if (position == 0) {
            binding.tab1.isSelected = true
            binding.tab2.isSelected = false
        } else {
            binding.tab1.isSelected = false
            binding.tab2.isSelected = true
        }
        if (binding.viewPager.currentItem != position) binding.viewPager.setCurrentItem(position, true)
    }

}