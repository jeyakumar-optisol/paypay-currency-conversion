package io.paypay.currency.ui.main

import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.asFlow
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import io.paypay.currency.R
import io.paypay.currency.databinding.ActivityMainBinding
import io.paypay.domain.datasource.IPreferenceStorage
import io.paypay.ui.base.BaseActivity
import io.paypay.utility.debug.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
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
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                while (true) {
                    viewModel.fetchCurrencyList().collect()
                    delay(TimeUnit.MINUTES.toMillis(30))
                }
            }
        }
        viewModel.currencyLiveData.observe(this) {
            Log.e("JeyK","received a list $it")
            //currency list
            //todo: adapter
        }
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