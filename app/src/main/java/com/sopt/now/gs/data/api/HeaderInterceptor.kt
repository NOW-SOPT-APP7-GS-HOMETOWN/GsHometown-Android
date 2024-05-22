package com.sopt.now.gs.data.api

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader(MEMBER_ID, USER_ID.toString())
            .build()
        return chain.proceed(request)
    }

    companion object {
        const val MEMBER_ID = "memberId"
        const val USER_ID = 1
    }
}
