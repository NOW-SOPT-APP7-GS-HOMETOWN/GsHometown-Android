package com.sopt.now.gs.feature.util

import java.text.DecimalFormat

object PriceFormatter {
    fun formatPrice(price: Int): String {
        return DecimalFormat("#,###").format(price) + "원"
    }
}
