package com.sopt.now.gs.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseLikedDto(
    @SerialName("isLiked")
    val isLiked: Boolean,
)
