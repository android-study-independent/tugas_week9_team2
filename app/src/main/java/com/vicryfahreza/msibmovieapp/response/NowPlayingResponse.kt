package com.vicryfahreza.msibmovieapp.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    val id: Int?,
    val overview: String?,
    val popularity: Float?,
    @SerializedName("poster_path")
    val posterPath: String?,
    val releaseDate: String?,
    val title: String?,
    @SerializedName("vote_average")
    val voteAverage: Float?
)

data class NowPlayingResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results : List<MovieResponse>,
    @SerializedName("total_pages")
    val totalPage : Int,
    @SerializedName("total_results")
    val totalResults : Int
)


