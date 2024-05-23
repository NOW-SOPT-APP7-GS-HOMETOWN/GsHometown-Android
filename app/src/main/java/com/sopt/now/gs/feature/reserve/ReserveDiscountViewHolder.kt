package com.sopt.now.gs.feature.reserve

import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sopt.now.gs.R
import com.sopt.now.gs.data.response.ResponseReserveGspayDto
import com.sopt.now.gs.databinding.ItemReserveDiscountMenuBinding
import com.sopt.now.gs.feature.util.PriceFormatter

class ReserveDiscountViewHolder(private val binding: ItemReserveDiscountMenuBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(discountMenuData: ResponseReserveGspayDto.Product) {
        binding.run {
            ivReserveDiscountMenuImage.load(discountMenuData.image)
            tvReserveDiscountMenuTitle.text = discountMenuData.title
            tvReserveDiscountMenuPrice.text = PriceFormatter.formatPrice(discountMenuData.price)
            tvReserveDiscountMenuOriginalPrice.text =
                PriceFormatter.formatPrice(discountMenuData.originalPrice)
            tvReserveDiscountMenuOriginalPrice.paintFlags =
                tvReserveDiscountMenuOriginalPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG //취소선
            tvReserveDiscountMenuCardPrice.text =
                PriceFormatter.formatPrice(discountMenuData.cardPrice)

        }
    }
}