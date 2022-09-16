package io.blix.photosapp.ui.vendor

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.core.os.bundleOf
import dagger.hilt.android.AndroidEntryPoint
import io.blix.photosapp.databinding.FragmentVendorBinding
import io.blix.photosapp.ui.vendor.adapter.ticket.VendorTicketAdapter
import io.blix.photosapp.ui.vendor.dialog.add_to_cart.VendorAddToCartBottomSheet
import io.blix.photosapp.ui.vendor.dialog.date_picker.VendorDatePickerDialog
import io.blix.photosapp.ui.vendor.dialog.vendor_details.VendorDetailsBottomSheet
import io.digikraft.domain.model.event.item.EventItem
import io.digikraft.domain.model.ticket.TicketItem
import io.digikraft.ui.base.BaseFragment
import io.digikraft.utility.debug.Log
import io.digikraft.utility.fragment.launchAndRepeatWithViewLifecycle
import io.digikraft.utility.fragment.popBackStack
import io.digikraft.utility.threading.runOnDefaultThread
import io.digikraft.utility.threading.runOnMainThread
import io.digikraft.utility.toUIDateFormat
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class VendorFragment : BaseFragment<FragmentVendorBinding, VendorViewModel>(
    FragmentVendorBinding::inflate, VendorViewModel::class.java
) {

    private val eventItem: EventItem?
        get() = arguments?.getParcelable(ARG_EVENT_ITEM)
    private val vendorTicketAdapter: VendorTicketAdapter
        get() = (binding.recyclerView.adapter as VendorTicketAdapter)

    private var selectedDatePosition: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    override fun onInit(savedInstanceState: Bundle?) {
        super.onInit(savedInstanceState)
        initData()
        initCartList()
        initListener()
    }

    private fun initCartList() {
        launchAndRepeatWithViewLifecycle {
            viewModel.getCartCacheHookup(eventItem?.id ?: 0).collectLatest { cartList ->
                runOnDefaultThread {
                    val adapterList = arrayListOf<TicketItem>().apply {
                        addAll(vendorTicketAdapter.getList())
                    }
                    for (ticketItem in adapterList) {
                        ticketItem.cartEntity = cartList.firstOrNull { ticketItem.id == it.id }
                    }
                    runOnMainThread {
                        vendorTicketAdapter.submitList(adapterList)
                    }
                }
            }
        }
//        viewModel.getCartCacheHookup(eventItem?.id ?: 0)
//            .observe(this) { cartList: List<CartEntity> ->
//                runOnAsyncThread {
//                    val adapterList = arrayListOf<TicketItem>().apply {
//                        addAll(vendorTicketAdapter.getList())
//                    }
//                    for (ticketItem in adapterList) {
//                        ticketItem.cartEntity = cartList.firstOrNull { ticketItem.id == it.id }
//                    }
//                    runOnMainThread {
//                        vendorTicketAdapter.submitList(adapterList)
//                    }
//                }
//            }
    }

    private fun initData() {
        binding.recyclerView.apply {
            adapter = VendorTicketAdapter(itemListener)
        }
        eventItem?.let { item ->
            binding.titleTextView.text = item.name
        }
        setDateTextView()
    }

    private fun initListener() = with(binding) {
        backImageView.setOnClickListener { popBackStack() }
        readMoreTextView.setOnClickListener {
            VendorDetailsBottomSheet().also {
                it.arguments = bundleOf("item" to eventItem)
                it.show(childFragmentManager, "VendorDetailsBottomSheet")
            }
        }
        previousDayImageView.setOnClickListener {
            if (selectedDatePosition != 0) {
                selectedDatePosition -= 1
            }
            setDateTextView()
        }
        nextDayImageView.setOnClickListener {
            if (selectedDatePosition != (eventItem?.dates?.size ?: 0) - 1) {
                selectedDatePosition += 1
            }
            setDateTextView()
        }
        dateTextView.setOnClickListener {
            eventItem?.let { eventItem ->
                VendorDatePickerDialog().apply {
                    arguments = bundleOf(VendorDatePickerDialog.ARG_VENDOR_DATES to eventItem.dates)
                    setListener {
                        selectedDatePosition = it
                        setDateTextView()
                    }
                }.show(childFragmentManager, "")
            }

//            context?.let {
//                val dateTimeSelectedListener = object : OnDateTimeSelectedListener {
//                    override fun onDateTimeSelected(selectedDateTime: Calendar) {
//                        d("Selected date ${selectedDateTime.time}")
//                        dateTextView.text = selectedDateTime.time.toStringFormatted()
//                    }
//                }
//                val dateTimePickerDialog = DialogDateTimePicker(
//                    it,
//                    getCurrentDate(),
//                    12,
//                    dateTimeSelectedListener,
//                    "Select date and time"
//                )
//                dateTimePickerDialog.setCancelBtnColor(R.color.add_to_cart_enabled)
//                dateTimePickerDialog.setSubmitBtnColor(R.color.add_to_cart_enabled)
//                dateTimePickerDialog.setTitleTextColor(R.color.white)
//                dateTimePickerDialog.setDividerBgColor(R.color.main_color_transparent)
//                dateTimePickerDialog.show()
//            }
        }
    }

    private fun setDateTextView() {
        eventItem?.let {
            binding.dateTextView.text = it.dates?.get(selectedDatePosition)?.toUIDateFormat()
        }
        fetchTickets()
    }

    private fun fetchTickets() {
        eventItem?.let { eventItem ->
            viewModel.fetchEventTickets(
                eventItem.id,
                eventItem.dates?.get(selectedDatePosition),
                eventItem.dates?.get(selectedDatePosition)
            ).observe(viewLifecycleOwner) {
                vendorTicketAdapter.submitList(it)
            }
        }
    }

    private val itemListener = object : VendorTicketAdapter.ItemListener {
        override fun onItemSelected(position: Int, item: TicketItem) {
            VendorAddToCartBottomSheet(item).apply {}.show(
                childFragmentManager,
                "VendorAddToCartBottomSheet"
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    companion object {
        const val ARG_EVENT_ITEM = "event_item"
    }
}