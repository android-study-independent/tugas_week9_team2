package com.vicryfahreza.msibmovieapp.screens.upcoming

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.vicryfahreza.msibmovieapp.data.MediaViewModel

@Composable
fun UpcomingScreen(viewModel: MediaViewModel = viewModel()) {
    val upcomingItems = viewModel.getUpcomingMovies().collectAsLazyPagingItems()
    val activity = LocalContext.current as FragmentActivity
}

