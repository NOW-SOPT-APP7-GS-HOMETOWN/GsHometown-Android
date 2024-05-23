package com.sopt.now.gs.feature.reserve

import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.gs.R
import com.sopt.now.gs.databinding.ItemReserveDiscountMenuBinding

class ReserveDiscountViewHolder(private val binding: ItemReserveDiscountMenuBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(discountMenuData: ReserveDiscountEntity) {
        binding.run {
            ivReserveDiscountMenuImage.setImageResource(discountMenuData.image)
            tvReserveDiscountMenuTitle.text = discountMenuData.title
            tvReserveDiscountMenuPrice.text = itemView.context.getString(R.string.reserve_menu_price, discountMenuData.price)
            tvReserveDiscountMenuOriginalPrice.text = itemView.context.getString(R.string.reserve_menu_price, discountMenuData.originalPrice)

            tvReserveDiscountMenuOriginalPrice.paintFlags =
                tvReserveDiscountMenuOriginalPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG //취소선
            tvReserveDiscountMenuCardPrice.text =
                itemView.context.getString(R.string.reserve_menu_price, discountMenuData.cardPrice)

        }
    }
}