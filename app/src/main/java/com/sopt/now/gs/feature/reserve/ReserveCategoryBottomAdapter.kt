package com.sopt.now.gs.feature.reserve

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.sopt.now.gs.R

class ReserveCategoryBottomAdapter(
    val context: Context,
    private val items: MutableList<ReserveCategoryBottomEntity>
) :
    BaseAdapter() {
    override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_reserve_category_bottom, null)

        val currentItem = items[position]

        view.findViewById<ImageView>(R.id.iv_reserve_category_bottom_image)
            .setImageResource(currentItem.categoryImage)
        view.findViewById<TextView>(R.id.tv_reserve_category_bottom_title).text =
            currentItem.categoryTitle
        view.findViewById<TextView>(R.id.tv_reserve_category_bottom_price).text =
            context.getString(R.string.reserve_menu_price, currentItem.categoryPrice)
        view.findViewById<TextView>(R.id.tv_reserve_category_bottom_rate).text =
            currentItem.categoryRate.toString()
        view.findViewById<TextView>(R.id.tv_reserve_category_botom_review).text =
            context.getString(R.string.reserve_menu_review, currentItem.categoryReview)

        return view
    }

    override fun getCount(): Int = items.size

    override fun getItem(position: Int): ReserveCategoryBottomEntity = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

}