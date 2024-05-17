package com.sopt.now.gs.feature.reserve

import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.gs.databinding.ItemReserveDiscountMenuBinding

class DiscountMenuViewHolder(private val binding: ItemReserveDiscountMenuBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(discountMenuData: DiscountMenu) {
        binding.run {
            ivReserveDiscountMenuImage.setImageResource(discountMenuData.image)
            tvReserveDiscountMenuTitle.text = discountMenuData.title
            tvReserveDiscountMenuPrice.text = discountMenuData.price.toString()
            tvReserveDiscountMenuOriginalPrice.text = discountMenuData.originalPrice.toString()
            tvReserveDiscountMenuCardPrice.text = discountMenuData.cardPrice.toString()
        }
    }
}