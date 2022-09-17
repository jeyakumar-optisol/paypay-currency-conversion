package io.digikraft.photosapp.ui.menu_tickets

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import dagger.hilt.android.AndroidEntryPoint
import io.digikraft.photosapp.R
import io.digikraft.photosapp.databinding.FragmentMenuMyTicketsBinding
import io.digikraft.photosapp.ui.menu_market.adapter.ViewPagerAdapter
import io.digikraft.photosapp.ui.menu_tickets.active.ActiveTicketsFragment
import io.digikraft.photosapp.ui.menu_tickets.past.PastTicketsFragment
import io.digikraft.ui.base.BaseFragment

@AndroidEntryPoint
class MenuMyTicketsFragment : BaseFragment<FragmentMenuMyTicketsBinding, MyTicketViewModel>(
    FragmentMenuMyTicketsBinding::inflate, MyTicketViewModel::class.java
) {

    override fun onInit(savedInstanceState: Bundle?) {
        super.onInit(savedInstanceState)

        initUi()
        initListeners()
    }

    private fun initUi() {
        val list = ArrayList<Fragment>()
        list.add(ActiveTicketsFragment())
        list.add(PastTicketsFragment())
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

    private fun initListeners() {

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