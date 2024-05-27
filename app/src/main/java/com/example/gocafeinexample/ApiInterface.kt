package com.example.gocafeinexample

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("/?type=movie")
    fun searchMovies(
        @Query("apikey") apiKey: String?,
        @Query("s") searchKeyword: String?,
        @Query("page") page: Int
    ): Call<MovieResponse?>?
}