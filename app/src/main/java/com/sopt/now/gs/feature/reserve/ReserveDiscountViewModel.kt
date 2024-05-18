package com.sopt.now.gs.feature.reserve

import androidx.lifecycle.ViewModel
import com.sopt.now.gs.R

//임시 데이터
class ReserveDiscountViewModel : ViewModel() {
    val mockFriendList = listOf<ReserveDiscountMenu>(
        ReserveDiscountMenu(
            image = R.drawable.img_reserve_meat1,
            title = "예약)한돈구이용삼겹살(400G)",
            price = 9900,
            originalPrice = 12000,
            cardPrice = 8900,
            isGsDiscount = true,
            starRating = 0.0,
            reviewCount = 0
        ),
        ReserveDiscountMenu(
            image = R.drawable.img_reserve_meat2,
            title = "예약)한돈구이용삼겹살(400G)",
            price = 9900,
            originalPrice = 12000,
            cardPrice = 8900,
            isGsDiscount = true,
            starRating = 0.0,
            reviewCount = 0
        ),
        ReserveDiscountMenu(
            image = R.drawable.img_reserve_meat3,
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