package com.example.gocafeinexample

import com.google.gson.annotations.SerializedName

data class SearchResponseDataDTO(
    @SerializedName("Response")
    var response: String,
    @SerializedName("Search")
    var search: ArrayList<Search>,
    @SerializedName("TotalResult")
    var totalResults: String
) {
    data class Search(
        @SerializedName("Poster")
        var poster: String,
        @SerializedName("Title")
        var title: String,
        @SerializedName("Type")
        var type: String,
        @SerializedName("Year")
        var year: String,
        @SerializedName("imdID")
        var imdbID: String
    )
}