package io.digikraft.ui.dialog

import android.content.Context
import io.digikraft.shared.databinding.DialogProgressBarBinding
import io.digikraft.base.AbstractProgressDialog

class BasicProgressDialog(context: Context) : AbstractProgressDialog<DialogProgressBarBinding>(
    context, DialogProgressBarBinding::inflate
) {
    override fun onInit(binding: DialogProgressBarBinding) {

    }
}