package com.sopt.now.gs.data.api.service

import com.sopt.now.gs.data.response.ResponseReqresDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ReqresService {
    @GET("api/users")
    suspend fun getUserList(
        @Query("page") page: Int
    ): Response<ResponseReqresDto>
}
