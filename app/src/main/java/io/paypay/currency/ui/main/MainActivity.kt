package io.paypay.currency.ui.main

import android.R.layout
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import io.paypay.currency.R
import io.paypay.currency.databinding.ActivityMainBinding
import io.paypay.currency.ui.main.adapter.CurrencyAdapter
import io.paypay.domain.datasource.IPreferenceStorage
import io.paypay.domain.model.cart.CurrencyEntity
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

    private val currencyGridAdapter get() = (binding.recyclerView.adapter as? CurrencyAdapter)

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
        viewModel.currencyLiveData.observe(this) { currencyList: List<CurrencyEntity> ->
            Log.e("JeyK", "received a list $currencyList")
            ArrayAdapter(this,
                    layout.simple_spinner_item, currencyList.map { it.currency }).let {
                it.setDropDownViewResource(layout.simple_spinner_dropdown_item)
                binding.currencyAppCompatSpinner.adapter = it
            }

            binding.recyclerView.adapter = CurrencyAdapter().also {
                it.submitList(currencyList)
            }
        }
    }

    private fun initData() {
        //todo: handle intent data
    }

    private fun initListener() {
        lifecycleScope.launch {}
        binding.currencyAppCompatSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?,
                                        p1: View?,
                                        p2: Int,
                                        p3: Long) {
                CurrencyAdapter.SelectedCurrency = currencyGridAdapter?.currentList?.get(p2)
                currencyGridAdapter?.notifyDataSetChanged()
            }
        }
        binding.numberInputTextInputEditText.doAfterTextChanged {
            CurrencyAdapter.SelectedValue = it.toString().toFloatOrNull() ?: 0f
            currencyGridAdapter?.notifyDataSetChanged()
        }
    }

    private fun initUi() {
        //todo: handle ui update or drawings
    }
}