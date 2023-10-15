package com.vicryfahreza.msibmovieapp.api


import NowPlayingResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Routes {
    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("language") lang: String = "id-ID",
        @Query("page") page: Int,
    ): NowPlayingResponse
}