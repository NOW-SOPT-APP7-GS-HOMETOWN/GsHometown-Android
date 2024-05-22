package com.sopt.now.gs.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseHomeDto(
    @SerialName("topBanners")
    val topBanners: List<String>,
    @SerialName("monthlyEvents")
    val monthlyEvents: MonthlyEvents,
    @SerialName("bottomBanners")
    val bottomBanners: List<String>,
) {
    @Serializable
    data class MonthlyEvents(
        @SerialName("mainBanners")
        val mainBanners: List<String>,
        @SerialName("subBanners")
        val subBanners: List<String>
    )
}