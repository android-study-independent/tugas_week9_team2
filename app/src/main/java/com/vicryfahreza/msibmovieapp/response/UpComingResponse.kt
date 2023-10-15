package com.vicryfahreza.msibmovieapp.response

import MovieResponse
import com.google.gson.annotations.SerializedName

data class UpComingResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<MovieResponse>,
    @SerializedName("total_pages")
    val totalPage: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
