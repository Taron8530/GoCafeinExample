package com.example.gocafeinexample

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("/?")
    fun searchMovies(
        @Query("apikey") apiKey: String,
        @Query("s") movieName: String,
        @Query("page") page : Int
    ): Call<SearchResponseDataDTO>
    @GET("/?")
    fun detailInformation(
        @Query("apikey") apiKey: String,
        @Query("i") imdbID: String
    ): Call<DetailResponseDataDTO>
}