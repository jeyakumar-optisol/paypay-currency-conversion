package io.paypay.ui.base

import android.os.Bundle
import android.util.Log
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.viewModelScope
import androidx.viewbinding.ViewBinding
import io.paypay.base.AbstractActivity
import io.paypay.domain.model.LoaderProperties
import io.paypay.ui.dialog.BasicProgressDialog
import io.paypay.utility.nullablity.orElse
import kotlinx.coroutines.launch

open class BaseActivity<B : ViewDataBinding, VM : BaseViewModel>(
    bindingFactory: Int,
    viewModelClass: Class<VM>,
) : AbstractActivity<B, VM>(bindingFactory, viewModelClass) {

    protected val viewModel: VM get() = abstractViewModel
    private var progressDialog: BasicProgressDialog? = null

    override fun onInit(savedInstanceState: Bundle?) {

        initObserver()
    }

    private fun initObserver() {

        viewModel.liveDataProgressBar.observe(this) { loaderProperties ->
            if (loaderProperties.show) {
                progressDialog?.setProgress(loaderProperties.progress).orElse {
                    progressDialog = BasicProgressDialog(this)
                    progressDialog?.setCancelable(loaderProperties.cancellable)
                    progressDialog?.setOnCancelListener {
                        abstractViewModel.onProgressDialogCancelled()
                    }
                    progressDialog?.setOnDismissListener {
                        progressDialog = null
                    }
                }
                progressDialog?.setMessage(loaderProperties.message)?.build()
            } else {
                progressDialog?.dismiss()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        progressDialog?.dismiss()
    }

}