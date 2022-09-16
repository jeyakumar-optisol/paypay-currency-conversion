package io.blix.photosapp.ui.introduction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import io.blix.photosapp.R
import io.blix.photosapp.databinding.FragmentIntroductionFinalBinding
import io.digikraft.ui.base.BaseFragment
import io.digikraft.utility.fragment.goto

class IntroductionFinalFragment : BaseFragment<FragmentIntroductionFinalBinding, IntroductionViewModel>(
    FragmentIntroductionFinalBinding::inflate,
    IntroductionViewModel::class.java
) {

    override fun onInit(savedInstanceState: Bundle?) {
        super.onInit(savedInstanceState)

        initData()
        initListener()
    }

    private fun initData() {
        binding.title = "Test 3"
        binding.subtitle = "Digitix let’s you purchase tickets safely and store them as unique memories in the form of NFTs."
    }

    private fun initListener() {
        binding.startButton.setOnClickListener {
            goto(R.id.goto_sign_in_fragment)
        }
    }

}