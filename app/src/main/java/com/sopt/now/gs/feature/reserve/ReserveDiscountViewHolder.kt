package com.sopt.now.gs.feature.reserve

import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.gs.databinding.ItemReserveDiscountMenuBinding

class ReserveDiscountViewHolder(private val binding: ItemReserveDiscountMenuBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(discountMenuData: ReserveDiscountMenu) {
        binding.run {
            ivReserveDiscountMenuImage.setImageResource(discountMenuData.image)
            tvReserveDiscountMenuTitle.text = discountMenuData.title
            tvReserveDiscountMenuPrice.text = discountMenuData.price.toString()
            tvReserveDiscountMenuOriginalPrice.text = discountMenuData.originalPrice.toString()
            tvReserveDiscountMenuOriginalPrice.paintFlags = tvReserveDiscountMenuOriginalPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG //취소선
            tvReserveDiscountMenuCardPrice.text = discountMenuData.cardPrice.toString() }
    }
}