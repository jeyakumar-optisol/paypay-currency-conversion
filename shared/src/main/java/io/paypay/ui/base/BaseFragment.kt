package io.paypay.ui.base

import android.os.Bundle
import androidx.lifecycle.viewModelScope
import androidx.viewbinding.ViewBinding
import io.paypay.base.AbstractFragment
import io.paypay.base.Inflate
import io.paypay.ui.dialog.BasicProgressDialog
import io.paypay.utility.debug.Log
import io.paypay.utility.nullablity.orElse
import kotlinx.coroutines.launch

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel>(
    inflate: Inflate<VB>,
    viewModelClass: Class<VM>
) : AbstractFragment<VB, VM>(inflate, viewModelClass) {

    private var progressDialog: BasicProgressDialog? = null
    protected val viewModel: VM get() = abstractViewModel
    val currentActivity get() = activity as BaseActivity<*, *>

    override fun onInit(savedInstanceState: Bundle?) {
        super.onInit(savedInstanceState)

        initObserver()
    }

    private fun initObserver() {
        viewModel.viewModelScope.launch {
            abstractViewModel.errorApiResult.collect {
//                Log.d(it.throwable?.localizedMessage ?: "error occurred on network event")

                with(it.message) {
                    Log.e(this)
                    toast(this)
                }
//                showMessage(it.message) todo:
            }
        }

        abstractViewModel.liveDataProgressBar.observe(viewLifecycleOwner) { loaderProperties ->
            if (loaderProperties.show) {
                progressDialog?.setProgress(loaderProperties.progress).orElse {
                    context?.let {
                        progressDialog = BasicProgressDialog(it)
                        progressDialog?.setCancelable(loaderProperties.cancellable)
                        progressDialog?.setOnCancelListener {
                            abstractViewModel.onProgressDialogCancelled()
                        }
                        progressDialog?.setOnDismissListener {
                            progressDialog = null
                        }
                    }
                }
                progressDialog?.setMessage(loaderProperties.message)?.build()
            } else {
                progressDialog?.dismiss()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        progressDialog?.dismiss()
    }
}