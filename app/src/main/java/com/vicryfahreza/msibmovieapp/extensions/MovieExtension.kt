package com.vicryfahreza.msibmovieapp.extensions

import android.annotation.SuppressLint
import com.vicryfahreza.msibmovieapp.constants.IMAGE_BASE_URL
import com.vicryfahreza.msibmovieapp.constants.ImageSize
import com.vicryfahreza.msibmovieapp.data_models.IMovie
import com.vicryfahreza.msibmovieapp.data_models.MediaBsData
import com.vicryfahreza.msibmovieapp.data_models.Movie
import com.vicryfahreza.msibmovieapp.helpers.getGenresText
import java.text.SimpleDateFormat
import java.util.*

fun IMovie.getPosterUrl(size: ImageSize = ImageSize.NORMAL): String {
    return "$IMAGE_BASE_URL${size.value}${this.posterPath}"
}

fun IMovie.getBackdropUrl(size: ImageSize = ImageSize.ORIGINAL): String {
    return "$IMAGE_BASE_URL${size.value}${this.backdropPath}"
}

fun Movie.getGenresText(): String? {
    return getGenresText(genreIds)
}

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
@SuppressLint("SimpleDateFormat")
fun IMovie.getReleaseDayMonth(): String? {
    return if (this.releaseDate == null) {
        null
    } else {
        val format = SimpleDateFormat("yyyy-MM-dd")
        val date: Date = format.parse(this.releaseDate)
        val df = SimpleDateFormat("MMMM dd")
        return df.format(date)
    }
}

@SuppressLint("SimpleDateFormat")
fun IMovie.getReleaseYear(): String? {
    return if (this.releaseDate == null) {
        null
    } else {
        val format = SimpleDateFormat("yyyy-MM-dd")
        val date: Date = format.parse(this.releaseDate)
        val df = SimpleDateFormat("yyyy")
        val year = df.format(date)
        year
    }
}

fun IMovie.toMediaBsData(): MediaBsData {
    return MediaBsData(
        "movie",
        this.id,
        this.getPosterUrl(),
        this.title,
        this.getReleaseYear(),
        this.overview
    )
}