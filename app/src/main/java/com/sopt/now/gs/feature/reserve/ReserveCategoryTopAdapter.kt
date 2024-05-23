package com.sopt.now.gs.feature.reserve

import android.content.Context
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

class ReserveCategoryTopAdapter(
    val context: Context,
    private val items: List<ResponseReserveCategoryDto.Product>,
    private val onItemClicked: (Int) -> Unit,
) : BaseAdapter() {
    override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.item_reserve_category_top, null)

        val currentItem = items[position]

        view.findViewById<ImageView>(R.id.iv_reserve_category_top_image)
            .load(currentItem.image)
        view.findViewById<TextView>(R.id.tv_reserve_category_top_menu_title).text =
            currentItem.title
        view.findViewById<TextView>(R.id.tv_reserve_category_top_price).text =
            PriceFormatter.formatPrice(currentItem.price)

        view.setOnClickListener {
            onItemClicked(position)
        }

        return view
    }

    override fun getCount(): Int = items.size

    override fun getItem(position: Int): ResponseReserveCategoryDto.Product = items[position]

    override fun getItemId(position: Int): Long = position.toLong()
}
