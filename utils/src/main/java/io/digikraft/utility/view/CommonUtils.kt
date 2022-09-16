package io.digikraft.utility.view

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners

fun View.setViewAndChildrenEnabled(enabled: Boolean) {
    this.isEnabled = enabled
    if (this is ViewGroup) {
        val viewGroup = this
        for (i in 0 until viewGroup.childCount) {
            val child = viewGroup.getChildAt(i)
            child.setViewAndChildrenEnabled(enabled)
        }
    }
}

fun ImageView.loadImage(src: String? = null) {
    Glide.with(this.context)
        .load(src)
        .centerCrop()
        .transform(CenterCrop(), GranularRoundedCorners(36f, 0f, 36f, 0f))
        .into(this)
}