package com.sopt.now.gs.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseReserveCategoryDto(
    @SerialName("category")
    val category: String,
    @SerialName("products")
    val products: List<Product>
) {
    @Serializable
    data class Product(
        @SerialName("productId")
        val productId: Int,
        @SerialName("image")
        val image: String,
        @SerialName("title")
        val title: String,
        @SerialName("price")
        val price: Int,
        @SerialName("originalPrice")
        val originalPrice: Int?,
        @SerialName("cardPrice")
        val cardPrice: Int?,
        @SerialName("isGsDiscount")
        val isGsDiscount: Boolean,
        @SerialName("starRating")
        val starRating: Double,
        @SerialName("reviewCount")
        val reviewCount: Int
    )
}