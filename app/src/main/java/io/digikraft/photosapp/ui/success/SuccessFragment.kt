package io.digikraft.photosapp.ui.success

import android.os.Bundle
import io.digikraft.photosapp.R
import io.digikraft.photosapp.databinding.FragmentSuccessBinding
import io.digikraft.ui.base.BaseFragment
import io.digikraft.utility.fragment.goto

class SuccessFragment : BaseFragment<FragmentSuccessBinding, SuccessViewModel>(
    FragmentSuccessBinding::inflate, SuccessViewModel::class.java
) {


    override fun onInit(savedInstanceState: Bundle?) {
        super.onInit(savedInstanceState)

        binding.mainPageButton.setOnClickListener {
            goto(R.id.goto_home_page)
        }
    }
}