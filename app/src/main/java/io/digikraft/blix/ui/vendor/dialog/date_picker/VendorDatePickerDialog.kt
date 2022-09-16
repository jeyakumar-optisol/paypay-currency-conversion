package io.blix.photosapp.ui.vendor.dialog.date_picker

import com.google.android.material.bottomsheet.BottomSheetBehavior
import io.digikraft.base.BaseBottomSheetDialogFragment
import io.blix.photosapp.databinding.DialogVendorDatePickerBinding
import io.blix.photosapp.ui.vendor.dialog.date_picker.adapter.VendorDatePickAdapter

class VendorDatePickerDialog : BaseBottomSheetDialogFragment<DialogVendorDatePickerBinding, VendorDatePickerViewModel>(
    DialogVendorDatePickerBinding::inflate, VendorDatePickerViewModel::class.java
) {

    private var listener: ((Int) -> Unit?)? = null
    private val list: ArrayList<String>?
        get() = arguments?.getStringArrayList(ARG_VENDOR_DATES)

    fun setListener(listener: (Int) -> Unit) {
        this.listener = listener
    }

    override fun onInit() {
        binding.dismissImageView.setOnClickListener { dismiss() }
        binding.recyclerView.apply {
            adapter = VendorDatePickAdapter {
                listener?.invoke(it)
                dismiss()
            }
            list?.let {
                (binding.recyclerView.adapter as VendorDatePickAdapter).submitList(it)
            }
        }
    }

    override fun initialExpandState(): Int {
        return BottomSheetBehavior.STATE_EXPANDED
    }

    override fun setDraggable(): Boolean {
        return false
    }

    companion object {
        const val ARG_VENDOR_DATES = "dates"
    }

}