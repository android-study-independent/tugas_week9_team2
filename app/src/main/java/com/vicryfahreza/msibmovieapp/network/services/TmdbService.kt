package com.vicryfahreza.msibmovieapp.network.services

import com.vicryfahreza.msibmovieapp.data_models.Media
import com.vicryfahreza.msibmovieapp.data_models.Movie
import com.vicryfahreza.msibmovieapp.network.model.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbService {
    @GET("trending/all/{time_window}")
    suspend fun fetchTrending(
        @Path("time_window") timeWindow: String,
        @Query("page") page: Int,
    ): PageResponse<Media>

    @GET("movie/upcoming")
    suspend fun fetchUpcomingMovies(@Query("page") page: Int): PageResponse<Movie>

    @GET("movie/popular")
    suspend fun fetchPopularMovies(@Query("page") page: Int): PageResponse<Movie>

    @GET("movie/{id}?append_to_response=similar,videos")
    suspend fun fetchMovieDetails(@Path("id") movieId: Int): MovieDetailsResponse

    @GET("movie/{id}/similar")
    suspend fun fetchSimilarMovies(@Path("id") movieId: Int): PageResponse<Movie>

    @GET("movie/{id}/videos")
    suspend fun fetchMovieVideos(@Path("id") movieId: Int): VideosResponse

    @GET("search/multi")
    suspend fun fetchSearchResults(
        @Query("query") query: String,
        @Query("page") page: Int,
    ): MediaResponse

    @GET("discover/movie")
    suspend fun discoverMovies(
        @Query("with_genres") withGenres: String?,
        @Query("sort_by") sortBy: String?,
        @Query("vote_count.gte") voteCountGreater: Int?,
        @Query("with_watch_providers") withWatchProviders: Int?,
        @Query("watch_region") watchRegion: String?,
    ): PageResponse<Movie>

}