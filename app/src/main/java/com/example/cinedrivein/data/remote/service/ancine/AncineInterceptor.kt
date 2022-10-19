package com.example.cinedrivein.data.remote.service.ancine

import okhttp3.Interceptor
import okhttp3.Response

class AncineInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", "jltv1cdnoitt3ud9jnp2p16br90e8i")
            .addHeader("Content-Type", "application/json")
            .build()

        return chain.proceed(request)
    }
}