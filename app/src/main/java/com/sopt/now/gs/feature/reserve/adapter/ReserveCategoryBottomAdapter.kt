package com.sopt.now.gs.feature.reserve.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.sopt.now.gs.R
import com.sopt.now.gs.data.response.ResponseReserveCategoryDto
import com.sopt.now.gs.feature.util.PriceFormatter

class ReserveCategoryBottomAdapter(
    val context: Context,
    private val items: List<ResponseReserveCategoryDto.Product>,
    private val onItemClicked: (Int) -> Unit,
) : BaseAdapter() {
    override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.item_reserve_category_bottom, null)

        val currentItem = items[position]

        view.findViewById<ImageView>(R.id.iv_reserve_category_bottom_image)
            .load(currentItem.image)
        view.findViewById<TextView>(R.id.tv_reserve_category_bottom_title).text =
            currentItem.title
        view.findViewById<TextView>(R.id.tv_reserve_category_bottom_price).text =
            PriceFormatter.formatPrice(currentItem.price)
        view.findViewById<TextView>(R.id.tv_reserve_category_bottom_rate).text =
            currentItem.starRating.toString()
        view.findViewById<TextView>(R.id.tv_reserve_category_botom_review).text =
            context.getString(R.string.reserve_menu_review, currentItem.reviewCount)

        view.setOnClickListener {
            onItemClicked(currentItem.productId)
            Log.e("pro",currentItem.productId.toString())
        }

        return view
    }

    override fun getCount(): Int = items.size

    override fun getItem(position: Int): ResponseReserveCategoryDto.Product = items[position]

    override fun getItemId(position: Int): Long = position.toLong()
}
