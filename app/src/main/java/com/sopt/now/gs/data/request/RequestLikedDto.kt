package com.sopt.now.gs.data.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestLikedDto(
    @SerialName("productId")
    val productId: Long,
)
