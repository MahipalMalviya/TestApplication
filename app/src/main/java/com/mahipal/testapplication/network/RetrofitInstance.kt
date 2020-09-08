package com.mahipal.testapplication.network

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private var retrofit: Retrofit? = null
    private const val BASE_URL = "http://www.omdbapi.com/"

    fun getInstance(context: Context): APIService? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit?.create(APIService::class.java)
    }
}