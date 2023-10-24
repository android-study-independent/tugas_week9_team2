package com.vicryfahreza.msibmovieapp.ui.detail

import MovieResponse
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import com.vicryfahreza.msibmovieapp.R
import com.vicryfahreza.msibmovieapp.model.Favorite
import java.util.Date

class DetailMovieActivity : AppCompatActivity() {
    val db  = Firebase.firestore
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
            val btnFavorite = findViewById<ImageButton>(R.id.btnFavorite)


            tvTitle.text = movie.title
            tvDescription.text = movie.overview
            tvRating.text = "${movie.voteAverage}"

            Picasso.get().load(urlImage).into(ivDetailMovie)


            btnFavorite.setOnClickListener {
                val currentUser = Firebase.auth.currentUser
                val title = tvTitle.text.toString()
                val description = tvDescription.text.toString()
                val favorite = Favorite(
                    url = "https://image.tmdb.org/t/p/w500/${movie.posterPath}",
                    title = title,
                    description = description,
                    createdAt = Date()
                )

                val dataUser = mapOf(
                    "name" to "${currentUser?.displayName}",
                    "email" to "${currentUser?.email}"
                )

                db.collection(PATH_FAVORITES)
                    .document(currentUser?.uid ?: "")
                    .set(dataUser)

                db.collection(PATH_FAVORITES)
                    .document(currentUser?.uid ?: "")
                    .collection(PATH_USER_FAVORITES)
                    .add(favorite)
                    .addOnSuccessListener {
                        Log.d("tag", "success simpan ${it.toString()}")
                        Toast.makeText(this, "Menambahkan movie ${tvTitle.text}", LENGTH_SHORT)
                            .show()
                        // success
                        finish()
                    }
                    .addOnFailureListener {
                        Log.e("tag", "gagal simpan ${it.localizedMessage}")
                    }
            }

        }
    }

    companion object {
        const val PATH_FAVORITES = "favorites"
        const val PATH_USER_FAVORITES = "user_favorites"
    }


}
