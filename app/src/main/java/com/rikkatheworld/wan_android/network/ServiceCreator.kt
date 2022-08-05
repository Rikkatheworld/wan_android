package com.rikkatheworld.wan_android.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {

    private const val BASE_URL = "https://www.wanandroid.com/"

    private fun create(): Retrofit =
        Retrofit.Builder().apply {
            baseUrl(BASE_URL)
            addConverterFactory(GsonConverterFactory.create())
        }.build()

    fun <T> create(service: Class<T>): T = create().create(service)
}