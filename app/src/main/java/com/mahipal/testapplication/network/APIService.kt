package com.mahipal.testapplication.network

import com.mahipal.testapplication.mvvm.model.BaseResponse
import retrofit2.Call
import retrofit2.http.GET

interface APIService {

    @GET("?i=tt3896198&apikey=5625bfd8")
    fun getMovieList(): Call<BaseResponse>
}