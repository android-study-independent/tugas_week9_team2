package com.vicryfahreza.msibmovieapp.network.model

import com.vicryfahreza.msibmovieapp.data_models.Media
import com.vicryfahreza.msibmovieapp.data_models.Movie
import com.squareup.moshi.Json

data class MediaResponse(
        @Json(name = "page") val page: Int,
        @Json(name = "results") val results: List<Media>,
        @Json(name = "total_pages") val totalPages: Int,
        @Json(name = "total_results") val totalResults: Int
)