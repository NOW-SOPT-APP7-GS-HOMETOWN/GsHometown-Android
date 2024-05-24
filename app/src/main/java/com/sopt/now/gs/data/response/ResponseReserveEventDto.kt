package com.sopt.now.gs.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseReserveEventDto(
    @SerialName("date")
    val date: String,
    @SerialName("headerTitle")
    val headerTitle: String,
    @SerialName("products")
    val products: List<Product>,
) {
    @Serializable
    data class Product(
        @SerialName("cardPrice")
        val cardPrice: Int? = null,
        @SerialName("image")
        val image: String,
        @SerialName("isGsDiscount")
        val isGsDiscount: Boolean,
        @SerialName("originalPrice")
        val originalPrice: Int? = null,
        @SerialName("price")
        val price: Int,
        @SerialName("productId")
        val productId: Int,
        @SerialName("reviewCount")
        val reviewCount: Int,
        @SerialName("starRating")
        val starRating: Double,
        @SerialName("title")
        val title: String,
    )
}
