package com.vicryfahreza.msibmovieapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.vicryfahreza.msibmovieapp.data_models.Movie
import com.vicryfahreza.msibmovieapp.network.services.ApiClient
import kotlinx.coroutines.flow.Flow

val defaultPagingConfig = PagingConfig(
    pageSize = MediaRepository.NETWORK_PAGE_SIZE,
    enablePlaceholders = false
)

object MediaRepository {
    private var upcomingMovies: Flow<PagingData<Movie>>? = null
    private var popularMovies: Flow<PagingData<Movie>>? = null

    fun getUpcomingMovies(): Flow<PagingData<Movie>> {
        if (upcomingMovies != null) {
            return upcomingMovies!!
        }
        upcomingMovies = Pager(config = defaultPagingConfig,
            pagingSourceFactory = { UpcomingMoviesPagingSource() }).flow
        return upcomingMovies!!
    }
    suspend fun discoverMovies(
        withGenres: String? = null,
        sortBy: String? = null,
        voteCountGreater: Int? = null,
        withWatchProviders: Int? = null,
        watchRegion: String? = null,
    ) = ApiClient.TMDB.discoverMovies(withGenres, sortBy, voteCountGreater, withWatchProviders, watchRegion)

    const val NETWORK_PAGE_SIZE = 20
}