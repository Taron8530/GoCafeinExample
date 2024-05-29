package com.example.gocafeinexample

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SecondActivityModel {
    fun getMovieDetails(apiKey : String, imdbID : String,callback: (DetailResponseDataDTO?) -> Unit) {
        val call = ApiClient.getRetrofit().create(ApiInterface :: class.java).detailInformation(apiKey,imdbID)
        call.enqueue(object : Callback<DetailResponseDataDTO> {
            override fun onResponse(
                p0: Call<DetailResponseDataDTO>,
                p1: Response<DetailResponseDataDTO>
            ) {
                if(p1.isSuccessful) {
                    callback(p1.body())
                }else {
                    callback(null)
                }
            }

            override fun onFailure(p0: Call<DetailResponseDataDTO>, p1: Throwable) {
                callback(null)
            }

        })
    }
}