package com.sopt.now.gs.feature.reserve

import androidx.lifecycle.ViewModel
import com.sopt.now.gs.R

//임시 데이터
class DiscountMenuViewModel : ViewModel() {
    val mockFriendList = listOf<DiscountMenu>(
        DiscountMenu(
            image = R.drawable.ic_launcher_background,
            title = "예약)한돈구이용삼겹살(400G)",
            price = 9900,
            originalPrice = 12000,
            cardPrice = 8900,
            isGsDiscount = true,
            starRating = 0.0,
            reviewCount = 0
        ),
        DiscountMenu(
            image = R.drawable.ic_launcher_background,
            title = "예약)한돈구이용삼겹살(400G)",
            price = 9900,
            originalPrice = 12000,
            cardPrice = 8900,
            isGsDiscount = true,
            starRating = 0.0,
            reviewCount = 0
        ),
        DiscountMenu(
            image = R.drawable.ic_launcher_background,
            title = "예약)한돈구이용삼겹살(400G)",
            price = 9900,
            originalPrice = 12000,
            cardPrice = 8900,
            isGsDiscount = true,
            starRating = 0.0,
            reviewCount = 0
        )
    )
}