package io.blix.photosapp.ui.vendor.dialog.add_to_cart

import android.content.DialogInterface
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import io.digikraft.base.BaseBottomSheetDialogFragment
import io.blix.photosapp.R
import io.blix.photosapp.databinding.DialogVendorAddToCardBinding
import io.digikraft.domain.model.ticket.TicketItem
import io.digikraft.utility.view.setViewAndChildrenEnabled

@AndroidEntryPoint
class VendorAddToCartBottomSheet(private val item: TicketItem) :
    BaseBottomSheetDialogFragment<DialogVendorAddToCardBinding, VendorAddToCartViewModel>(
        DialogVendorAddToCardBinding::inflate,
        VendorAddToCartViewModel::class.java
    ) {

    private val currentCount: Int get() = binding.countTextView.text.toString().toInt()

    override fun onInit() {
        initData()
        initListener()
        initPreview()
    }

    private fun initPreview() {
        binding.count = item.cartEntity?.quantity ?: 0
        binding.addToCartMaterialButton.setViewAndChildrenEnabled(binding.count > 0)
    }

    private fun initData() {
        viewModel.getItemCartCount {
            binding.countTextView.text = it.toString()
            binding.addToCartMaterialButton.setViewAndChildrenEnabled(it > 0)
        }
    }

    private fun initListener() = with(binding) {
        dismissImageView.setOnClickListener { dismiss() }
        addToCartMaterialButton.setOnClickListener {
            viewModel.updateCartCount(
                item = item,
                count = currentCount
            ).observe(viewLifecycleOwner) {
                dismiss()
            }
        }
        val onClickListener = View.OnClickListener {
            if (it.id == R.id.minusFromCartImageButton) {
                if (currentCount > 0) {
                    countTextView.text = (currentCount - 1).toString()
                }
            } else {
                if ((currentCount + 1) <= item.quantity) {
                    countTextView.text = (currentCount + 1).toString()
                }
            }
            addToCartMaterialButton.setViewAndChildrenEnabled(currentCount > 0)
        }
        minusFromCartImageButton.setOnClickListener(onClickListener)
        addToCartImageButton.setOnClickListener(onClickListener)
    }

    override fun initialExpandState(): Int {
        return BottomSheetBehavior.STATE_EXPANDED
    }

    override fun setDraggable(): Boolean {
        return false
    }

    override fun onDismiss(dialog: DialogInterface) {
        if (currentCount == 0 && item.cartEntity?.quantity != 0) {
            viewModel.updateCartCount(item, currentCount).observe(viewLifecycleOwner) {
                super.onDismiss(dialog)
            }
        } else {
            super.onDismiss(dialog)
        }
    }
}