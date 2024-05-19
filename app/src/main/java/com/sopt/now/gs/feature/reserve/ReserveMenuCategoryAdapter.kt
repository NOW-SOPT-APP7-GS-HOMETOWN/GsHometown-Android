package com.sopt.now.gs.feature.reserve

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.sopt.now.gs.R

class GridMenuCategoryAdapter(
    val context: Context,
    private val items: MutableList<ReserveMenuCategoryItem>
) :
    BaseAdapter() {
    override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_reserve_category, null)

        val currentItem = items[position]

        view.findViewById<ImageView>(R.id.iv_reserve_category_image)
            .setImageResource(currentItem.menuCategoryImage)
        view.findViewById<TextView>(R.id.tv_reserve_category_title).text =
            currentItem.menuCategoryTitle
        view.findViewById<TextView>(R.id.tv_reserve_category_price).text =
            context.getString(R.string.reserve_menu_price, currentItem.menuCategoryPrice)
        return view
    }

    override fun getCount(): Int = items.size

    override fun getItem(position: Int): ReserveMenuCategoryItem = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

}