package com.example.exchange.model

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class PathInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        // Request customization: add request headers
        val requestBuilder: Request.Builder = original.newBuilder()
            .addHeader(API_KEY.API_VALUE, API_KEY.API_KEY) // <-- this is the important line
        val request: Request = requestBuilder.build()
        return chain.proceed(request)
    }
}