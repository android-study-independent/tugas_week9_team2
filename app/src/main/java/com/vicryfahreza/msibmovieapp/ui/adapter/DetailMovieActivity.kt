package com.vicryfahreza.msibmovieapp.ui.adapter

import MovieResponse
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.vicryfahreza.msibmovieapp.R

class DetailMovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        val movie = intent.getParcelableExtra<MovieResponse>("movie")

        if (movie != null) {
            val urlImage = "https://image.tmdb.org/t/p/w500/${movie.posterPath}"

            val tvTitle = findViewById<TextView>(R.id.tvTitle)
            val tvDescription = findViewById<TextView>(R.id.tvDescription)
            val tvRating = findViewById<TextView>(R.id.tvRating)
            val ivDetailMovie = findViewById<ImageView>(R.id.ivDetailMovie)

            tvTitle.text = movie.title
            tvDescription.text = movie.overview
            tvRating.text = "Rating: ${movie.voteAverage}"

            Picasso.get().load(urlImage).into(ivDetailMovie)
        }
    }
}
