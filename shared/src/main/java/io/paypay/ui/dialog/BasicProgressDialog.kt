package io.paypay.ui.dialog

import android.content.Context
import io.paypay.shared.databinding.DialogProgressBarBinding
import io.paypay.base.AbstractProgressDialog

class BasicProgressDialog(context: Context) : AbstractProgressDialog<DialogProgressBarBinding>(
    context, DialogProgressBarBinding::inflate
) {
    override fun onInit(binding: DialogProgressBarBinding) {

    }
}