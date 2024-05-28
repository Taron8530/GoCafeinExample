package com.example.gocafeinexample

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityModel {
    private val TAG = "MainActivityModel"
    private var page = 1
    fun searchMovie(apiKey: String, movieName: String, callback: (ArrayList<SearchResponseDataDTO.Search>?) -> Unit) {
        val call = ApiClient.getRetrofit().create(ApiInterface::class.java).searchMovies(apiKey, movieName,page)
        call.enqueue(object : Callback<SearchResponseDataDTO> {
            override fun onResponse(call: Call<SearchResponseDataDTO>, response: Response<SearchResponseDataDTO>) {
                if (response.isSuccessful) {
                    Log.d(TAG, "searchMovie: ${response.body()}")
                    callback(response.body()?.search)
                    page += 1
                } else {
                    // 서버로부터 정상적인 응답을 받지 못했을 때
                    Log.e(TAG, "searchMovie Error: ${response.errorBody()?.string()}")
                    callback(null)
                }
            }

            override fun onFailure(call: Call<SearchResponseDataDTO>, t: Throwable) {
                // 네트워크 요청 자체가 실패했을 때 (예: 인터넷 연결 문제)
                Log.e(TAG, "searchMovie Failure: ${t.message}")
                callback(null)
            }
        })
    }
}
