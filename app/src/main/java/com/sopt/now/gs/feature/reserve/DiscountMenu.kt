package com.sopt.now.gs.feature.reserve

import androidx.annotation.DrawableRes

data class DiscountMenu (
    @DrawableRes val image : Int,
    val title : String,
    val price : Int,
    val originalPrice : Int,
    val cardPrice : Int,
    val isGsDiscount:Boolean,
    val starRating : Double,
    val reviewCount : Int
)