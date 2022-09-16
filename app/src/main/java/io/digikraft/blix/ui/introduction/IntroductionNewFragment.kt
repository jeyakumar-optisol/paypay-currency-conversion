package io.blix.photosapp.ui.introduction

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import io.blix.photosapp.R
import io.blix.photosapp.databinding.FragmentIntroductionNewBinding
import io.digikraft.domain.datasource.IPreferenceStorage
import io.digikraft.ui.base.BaseFragment
import io.digikraft.utility.debug.Log
import io.digikraft.utility.debug.Log.d
import io.digikraft.utility.fragment.goto
import io.digikraft.utility.fragment.launchAndRepeatWithViewLifecycle
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class IntroductionNewFragment : BaseFragment<FragmentIntroductionNewBinding, IntroductionViewModel>(
    FragmentIntroductionNewBinding::inflate,
    IntroductionViewModel::class.java
) {

    private var itemNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            itemNumber = it.getInt(ITEM_NUMBER, 0)
        }
    }

    override fun onInit(savedInstanceState: Bundle?) {
        super.onInit(savedInstanceState)
        initData()
        initListener()
    }

    private fun initData() {
        binding.title = "Test ${itemNumber + 1}"
        binding.subtitle = "Digitix let’s you purchase tickets safely and store them as unique memories in the form of NFTs."
        binding.dotsView.setSelectedItem(itemNumber)
    }

    private fun initListener() {
        binding.nextButton.setOnClickListener {
            if (itemNumber == 1) {
                goto(R.id.goto_introduction_final)
            } else {
                goto(R.id.goto_introduction, bundleOf(ITEM_NUMBER to itemNumber + 1))
            }
        }
        binding.skipButton.setOnClickListener {
            goto(R.id.goto_sign_in_fragment)
        }
    }

    companion object {
        const val ITEM_NUMBER = "intro_item_number"
    }

}