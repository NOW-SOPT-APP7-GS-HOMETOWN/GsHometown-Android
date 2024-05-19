package com.sopt.now.gs.feature.reserve

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.sopt.now.gs.R

class GridMenuCategory2Adapter(
    val context: Context,
    private val items: MutableList<ReserveMenuCategory2Item>
) :
    BaseAdapter() {
    override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_reserve_category2, null)

        val currentItem = items[position]

        view.findViewById<ImageView>(R.id.iv_reserve_category2_image)
            .setImageResource(currentItem.menuCategoryImage)
        view.findViewById<TextView>(R.id.tv_reserve_category2_title).text =
            currentItem.menuCategoryTitle
        view.findViewById<TextView>(R.id.tv_reserve_category2_price).text =
            "${currentItem.menuCategoryPrice}원"
        view.findViewById<TextView>(R.id.tv_reserve_category2_star_rate).text =
            currentItem.menuCategoryRate.toString()
        view.findViewById<TextView>(R.id.tv_reserve_category2_review).text =
            "후기${currentItem.menuCategoryReview}"

        return view
    }

    override fun getCount(): Int = items.size

    override fun getItem(position: Int): ReserveMenuCategory2Item = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

}