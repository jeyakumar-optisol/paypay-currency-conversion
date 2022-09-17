package io.digikraft.photosapp.ui.vendor.dialog.vendor_details

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.text.color
import androidx.transition.TransitionManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import io.digikraft.base.BaseBottomSheetDialogFragment
import io.digikraft.photosapp.R
import io.digikraft.photosapp.databinding.DialogVendorDetailsBinding
import io.digikraft.photosapp.ui.vendor.dialog.vendor_details.adapter.OpeningHoursAdapter
import io.digikraft.domain.model.event.item.EventItem
import io.digikraft.utility.fragment.copyToClipboard
import io.digikraft.utility.fragment.toast


@AndroidEntryPoint
class VendorDetailsBottomSheet :
    BaseBottomSheetDialogFragment<DialogVendorDetailsBinding, VendorDetailsViewModel>(
        DialogVendorDetailsBinding::inflate, VendorDetailsViewModel::class.java
    ) {

    private var eventItem: EventItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            eventItem = it.getParcelable("item")
        }
    }

    override fun onInit() {
        initData()
        initMap()
        initListener()
    }

    @SuppressLint("MissingPermission")
    private fun initMap() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment?
        mapFragment?.getMapAsync { googleMap ->
            context?.let {
                /*Add marker into map*/
                val latLng = LatLng(41.311081, 69.240562)
                googleMap.addMarker(
                    MarkerOptions().position(latLng)
                        .icon(bitmapDescriptorFromVector(it, R.drawable.ic_map_pin))
                ).showInfoWindow()
                googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(it, R.raw.map_style_json))
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom((latLng), 11.0F))
//                googleMap.isMyLocationEnabled = false //showing current location
            }
        }
    }

    private fun initData() = with(binding) {
        eventItem?.let {
            val text = SpannableStringBuilder()
                .color(ContextCompat.getColor(requireContext(), R.color.color_green)) {
                    append("Open")
                }
                .append(" • Closes 7PM")
            timeTableTextView.setText(text, TextView.BufferType.SPANNABLE)
            urlTextView.text = it.siteUrl
            openingHoursRecyclerView.apply {
                adapter = OpeningHoursAdapter().apply {
                    submitList(it.openingHours)
                }
            }
        }
    }

    private fun initListener() = with(binding) {
        dismissImageView.setOnClickListener { dismiss() }
        copyLocationImageView.setOnClickListener {
            copyToClipboard("Event address", locationTextView.text.toString()) {
                toast("Address copied")
            }
        }
        timeTableTextView.setOnClickListener {
            showOpeningHours()
        }
        timeTableImageView.setOnClickListener {
            showOpeningHours()
        }
        urlOpenImageView.setOnClickListener {
            Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(eventItem?.siteUrl)
                startActivity(this)
            }
        }
    }

    private fun showOpeningHours() {
        binding.openingHoursRecyclerView.apply {
            visibility = if (visibility == View.VISIBLE) {
                View.GONE
            } else View.VISIBLE
            TransitionManager.beginDelayedTransition(binding.rootLayout)
        }
    }

    override fun initialExpandState(): Int {
        return BottomSheetBehavior.STATE_EXPANDED
    }

    override fun setDraggable(): Boolean {
        return false
    }

    private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        return ContextCompat.getDrawable(context, vectorResId)?.run {
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
            val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
            draw(Canvas(bitmap))
            BitmapDescriptorFactory.fromBitmap(bitmap)
        }
    }
}