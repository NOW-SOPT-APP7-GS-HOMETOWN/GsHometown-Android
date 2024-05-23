package com.sopt.now.gs.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponsePurchaseDetailDto(
    @SerialName("detailImage")
    val detailImage: String,
    @SerialName("isLiked")
    val isLiked: Boolean,
    @SerialName("isReceiveAvailable")
    val isReceiveAvailable: Boolean,
    @SerialName("price")
    val price: Int,
    @SerialName("reviewCount")
    val reviewCount: Int,
    @SerialName("starRating")
    val starRating: Double,
    @SerialName("thumbnail")
    val thumbnail: String,
    @SerialName("title")
    val title: String,
)
