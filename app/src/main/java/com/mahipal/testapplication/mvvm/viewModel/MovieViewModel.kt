package com.mahipal.testapplication.mvvm.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mahipal.testapplication.mvvm.model.BaseResponse
import com.mahipal.testapplication.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {

    fun searchImages(context:Context): MutableLiveData<BaseResponse> {
        val mutableMovieList = MutableLiveData<BaseResponse>()

        val apiService = RetrofitInstance.getInstance(context)
        val apiCall = apiService?.getMovieList()

        apiCall?.enqueue(object : Callback<BaseResponse> {
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    mutableMovieList.postValue(response.body())
                }
            }
        })
        return mutableMovieList
    }
}