package io.digikraft.photosapp.ui.vendor.dialog.pay

import com.google.android.material.bottomsheet.BottomSheetBehavior
import io.digikraft.base.BaseBottomSheetDialogFragment
import io.digikraft.photosapp.databinding.DialogCartPayBinding

class CartPayBottomSheet : BaseBottomSheetDialogFragment<DialogCartPayBinding, CartPayViewModel>(
    DialogCartPayBinding::inflate, CartPayViewModel::class.java
) {

    override fun onInit() {

    }


    override fun initialExpandState(): Int {
        return BottomSheetBehavior.STATE_COLLAPSED
    }

    override fun setDraggable(): Boolean {
        return true
    }

}