package io.paypay.currency.ui.main

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import dagger.hilt.android.AndroidEntryPoint
import io.paypay.currency.R
import io.paypay.currency.databinding.ActivityMainBinding
import io.paypay.domain.datasource.IPreferenceStorage
import io.paypay.ui.base.BaseActivity
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(
    R.layout.activity_main, MainViewModel::class.java
) {

    @Inject
    lateinit var preferenceStorage: IPreferenceStorage

    override fun onInit(savedInstanceState: Bundle?) {
        super.onInit(savedInstanceState)

        initListener()
        initObserver()
        initData()
        initUi()
    }

    private fun initObserver() {
        //todo:
    }

    private fun initData() {
        //todo: handle intent data
    }

    private fun initListener() {
        lifecycleScope.launch {}
    }

    private fun initUi() {
        //todo: handle ui update or drawings
    }
}