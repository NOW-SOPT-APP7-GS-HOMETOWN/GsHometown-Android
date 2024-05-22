package com.sopt.now.gs.data.api.service

import com.sopt.now.gs.data.response.BaseResponse
import com.sopt.now.gs.data.response.ResponseHomeDto
import com.sopt.now.gs.data.response.ResponseReqresDto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface GsHometownService {
    @GET("api/users")
    suspend fun getUserList(
        @Query("page") page: Int
    ): Response<ResponseReqresDto>

    @GET("api/")
    suspend fun getHomeImages(): BaseResponse<ResponseHomeDto>
}
