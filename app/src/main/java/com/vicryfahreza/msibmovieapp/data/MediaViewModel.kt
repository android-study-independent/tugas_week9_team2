package com.vicryfahreza.msibmovieapp.data

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.vicryfahreza.msibmovieapp.data_models.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MediaViewModel @Inject constructor() : ViewModel() {

    fun getUpcomingMovies(): Flow<PagingData<Movie>> {
        return MediaRepository.getUpcomingMovies()
    }
}