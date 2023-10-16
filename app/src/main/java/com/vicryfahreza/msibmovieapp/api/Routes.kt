package com.vicryfahreza.msibmovieapp.api


import NowPlayingResponse
import com.vicryfahreza.msibmovieapp.response.PopularResponse
import com.vicryfahreza.msibmovieapp.response.TopRatedResponse
import com.vicryfahreza.msibmovieapp.response.UpcomingResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Routes {
    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("language") lang: String = "en-EN",
        @Query("page") page: Int,
    ): NowPlayingResponse

    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("language") lang: String = "en-EN",
        @Query("page") page: Int,
    ): TopRatedResponse

    @GET("movie/upcoming")
    suspend fun getUpcoming(
        @Query("language") lang: String = "en-EN",
        @Query("page") page: Int
    ): UpcomingResponse

    @GET("movie/popular")
    suspend fun getPopular(
        @Query("language") lang: String = "en-EN",
        @Query("page") page: Int,
    ): PopularResponse

}