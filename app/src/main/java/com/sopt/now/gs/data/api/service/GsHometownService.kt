package com.sopt.now.gs.data.api.service

import com.sopt.now.gs.data.api.ApiKeyStorage.API
import com.sopt.now.gs.data.api.ApiKeyStorage.LIKES
import com.sopt.now.gs.data.api.ApiKeyStorage.PRODUCTS
import com.sopt.now.gs.data.api.ApiKeyStorage.PRODUCT_ID
import com.sopt.now.gs.data.request.RequestLikedDto
import com.sopt.now.gs.data.response.BaseResponse
import com.sopt.now.gs.data.response.ResponseHomeDto
import com.sopt.now.gs.data.response.ResponseLikedDto
import com.sopt.now.gs.data.response.ResponsePurchaseDetailDto
import com.sopt.now.gs.data.response.ResponseReserveEventDto
import com.sopt.now.gs.data.response.ResponseReserveGspayDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface GsHometownService {
    @GET("api/")
    suspend fun getHomeImages(): BaseResponse<ResponseHomeDto>

    @GET("$API/$PRODUCTS/{$PRODUCT_ID}")
    suspend fun getPurchaseDetail(
        @Path(PRODUCT_ID) productId: Long,
    ): Response<BaseResponse<ResponsePurchaseDetailDto>>

    @GET("$API/$PRODUCTS")
    suspend fun getGspay(
        @Query("type") type: String,
    ): Response<BaseResponse<ResponseReserveGspayDto>>

    @POST("$API/$LIKES")
    suspend fun postLiked(
        @Body productId: RequestLikedDto,
    ): Response<BaseResponse<ResponseLikedDto>>

    @HTTP(method = "DELETE", path = "$API/$LIKES", hasBody = true)
    suspend fun deleteLiked(
        @Body productId: RequestLikedDto,
    ): Response<BaseResponse<ResponseLikedDto>>

    @GET("$API/$PRODUCTS")
    suspend fun getReserveEvent(
        @Query("type") type: String,
    ): Response<BaseResponse<ResponseReserveEventDto>>
}
