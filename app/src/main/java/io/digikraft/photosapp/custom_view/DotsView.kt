package io.digikraft.photosapp.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.LinearLayoutCompat
import io.digikraft.photosapp.R
import io.digikraft.photosapp.databinding.ViewDotsBinding

class DotsView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayoutCompat(context, attrs) {

    private var binding: ViewDotsBinding
    private var dotsCount: Int = 3
    private var selectedItem = 0
    private val rootViews = ArrayList<ImageView>()

    init {
        binding = ViewDotsBinding.inflate(LayoutInflater.from(context), this, true)
        val a = resources.obtainAttributes(attrs, R.styleable.DotsView)
        dotsCount = a.getInt(R.styleable.DotsView_dotsItemCount, 3)
        selectedItem = a.getInt(R.styleable.DotsView_selectedItem, 0)
        a.recycle()
        repaint()
    }

    private fun repaint() {
        binding.rootLayout.removeAllViews()
        rootViews.clear()
        for (i in 0 until dotsCount) {
            val constructedView = constructInputView()
            val imageView = (constructedView.findViewById<View>(R.id.dotImageView) as ImageView)
            imageView.isSelected = i == selectedItem
            rootViews.add(imageView)
            binding.rootLayout.addView(constructedView)
        }
    }

    private fun constructInputView(): View {
        return LayoutInflater.from(context).inflate(R.layout.view_item_dot, binding.rootLayout, false)
    }

    fun getItemCount(): Int = dotsCount

    fun setSelectedItem(item: Int) {
        selectedItem = item
        repaint()
    }

}