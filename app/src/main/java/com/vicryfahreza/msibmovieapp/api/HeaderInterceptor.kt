package com.vicryfahreza.msibmovieapp.api

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    private val TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmM2E2YjJhOWExMjU2OWI2MjE5Y2RmYzg1ZmQzYjRlZSIsInN1YiI6IjY1MjUzYjljODNlZTY3MDEzYjdkZjc5OCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.R3C5IN6whyLwoJjaixhhSV7sK5VVR2ikiGUeQJ2s308"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $TOKEN").build()
        return chain.proceed(request)
    }
}