package com.vicryfahreza.msibmovieapp.ui

import MovieResponse
import NowPlayingAdapter
import NowPlayingListener
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.vicryfahreza.msibmovieapp.api.Network
import com.vicryfahreza.msibmovieapp.databinding.ActivityTopRatedBinding
import com.vicryfahreza.msibmovieapp.firebasedemo.SignInActivity
import com.vicryfahreza.msibmovieapp.ui.detail.DetailMovieActivity
import kotlinx.coroutines.launch

class TopRatedActivity : AppCompatActivity(), NowPlayingListener {
    private lateinit var binding: ActivityTopRatedBinding
    private lateinit var adapter: NowPlayingAdapter
    private var movieList = mutableListOf<MovieResponse>()
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopRatedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val rvTopRated = binding.rvTopRated

        adapter = NowPlayingAdapter(movieList, this)

        rvTopRated.layoutManager = GridLayoutManager(this, 2)
        rvTopRated.adapter = adapter

        auth = Firebase.auth
        val firebaseUser = auth.currentUser

        lifecycleScope.launch {
            val result = Network.getService(this@TopRatedActivity).getTopRated(
                page = 1
            )

            result.results.map {
                movieList.add(it)
            }
            adapter.notifyDataSetChanged()
        }


        binding.btnNowPlaying.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnPopular.setOnClickListener {
            val intent = Intent(this, PopularActivity::class.java)
            startActivity(intent)
        }

        binding.btnUpComing.setOnClickListener {
            val intent = Intent(this, UpcomingActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogout.setOnClickListener {
            signOut()
        }

    }

    private fun signOut() {
        auth.signOut()
        startActivity(Intent(this, SignInActivity::class.java))
        finish()
    }

    override fun onNowPlayingDetailClick(movie: MovieResponse) {
            val intent = Intent(this, DetailMovieActivity::class.java)
        intent.putExtra("movie", movie) // Mengirim objek movie ke DetailMovieActivity
        startActivity(intent)
    }
}