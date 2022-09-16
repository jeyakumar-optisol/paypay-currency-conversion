package io.digikraft.photosapp.ui.introduction

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import io.digikraft.photosapp.R
import io.digikraft.photosapp.databinding.FragmentIntroductionBinding
import io.digikraft.photosapp.ui.introduction.adapter.ViewPagerAdapter
import io.digikraft.ui.base.BaseFragment
import io.digikraft.utility.fragment.goto

class IntroductionFragment : BaseFragment<FragmentIntroductionBinding, IntroductionViewModel>(
    FragmentIntroductionBinding::inflate,
    IntroductionViewModel::class.java
) {
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onInit(savedInstanceState: Bundle?) {
        super.onInit(savedInstanceState)

        initData()
        initObserver()
        initListener()
        initAdapter()
        initUi()
    }

    private fun initData() {
        //todo: handle intent data
    }

    private fun initObserver() {
        //todo: handle observer livedata
    }

    private fun initListener() {
        binding.introductionViewPager.addOnPageChangeListener(object :
            ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                //noop
            }

            override fun onPageSelected(position: Int) {
                binding.isLast = viewPagerAdapter.itemList.size - 1 == position
            }

            override fun onPageScrollStateChanged(state: Int) {
                //noop
            }

        })
        binding.btnNext.setOnClickListener {
            binding.introductionViewPager.currentItem = binding.introductionViewPager.currentItem + 1
        }
        binding.btnSkip.setOnClickListener {
            initNext()
        }
        binding.btnStart.setOnClickListener {
            initNext()
        }
    }

    private fun initAdapter() {
        val mutableList = mutableListOf<ViewPagerAdapter.ItemModel>()
        for (i in 1..3) {
            mutableList.add(ViewPagerAdapter.ItemModel().apply {
                title = "Tickets $i"
                subtitle = "Digitix let’s you purchase tickets safely and store them as unique memories in the form of NFTs."
            })
        }
        viewPagerAdapter = ViewPagerAdapter(currentActivity, mutableList)
        binding.introductionViewPager.adapter = viewPagerAdapter
        binding.introductionDotsIndicator.attachTo(binding.introductionViewPager)
    }

    private fun initUi() {
        //todo: handle ui update or drawings
    }

    private fun initNext() {
//        goto(R.id.menuHomeFragment)
        goto(R.id.goto_sign_in_fragment)
    }
}