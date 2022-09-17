package io.digikraft.photosapp.ui.introduction.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import io.digikraft.photosapp.databinding.ItemViewpagerIntroductionBinding

class ViewPagerAdapter(
    private val context: Context,
    val itemList: List<ItemModel>
) :
    PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val item = itemList[position]
        return ItemViewpagerIntroductionBinding.inflate(
            LayoutInflater.from(context),
            container,
            false
        ).apply {
            //todo: this.introductionAppCompatImageView
            this.title = item.title
            this.subtitle = item.subtitle
            container.addView(this.root, position)
        }.root
    }

    override fun getCount(): Int {
        return itemList.size
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as ViewGroup
        container.removeView(view)
    }

    class ItemModel() {
        lateinit var title: String
        lateinit var subtitle: String
        var image: String? = null
    }
}