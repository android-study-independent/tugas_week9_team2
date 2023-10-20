package com.vicryfahreza.msibmovieapp.ui

import MovieResponse
import NowPlayingAdapter
import NowPlayingListener
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.vicryfahreza.msibmovieapp.R
import com.vicryfahreza.msibmovieapp.api.Network
import com.vicryfahreza.msibmovieapp.databinding.ActivityMainBinding
import com.vicryfahreza.msibmovieapp.databinding.ActivityPopularBinding
import com.vicryfahreza.msibmovieapp.firebasedemo.SignInActivity
import com.vicryfahreza.msibmovieapp.ui.detail.DetailMovieActivity
import kotlinx.coroutines.launch

class PopularActivity : AppCompatActivity(), NowPlayingListener {

    private lateinit var binding: ActivityPopularBinding
    private lateinit var adapter: NowPlayingAdapter
    private var movieList = mutableListOf<MovieResponse>()
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPopularBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val rvPopular = binding.rvPopular

        adapter = NowPlayingAdapter(movieList, this)

        rvPopular.layoutManager = GridLayoutManager(this, 2)
        rvPopular.adapter = adapter

        auth = Firebase.auth
        val firebaseUser = auth.currentUser

        lifecycleScope.launch {
            val result = Network.getService(this@PopularActivity).getPopular(
                page = 1
            )

            result.results.map {
                movieList.add(it)
            }
            adapter.notifyDataSetChanged()
        }


        binding.btnTopRated.setOnClickListener {
            val intent = Intent(this, TopRatedActivity::class.java)
            startActivity(intent)
        }

        binding.btnNowPlaying.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
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

        // Di sini, Anda akan memulai DetailMovieActivity saat ivMovie diklik.
        val intent = Intent(this, DetailMovieActivity::class.java)
        intent.putExtra("movie", movie) // Mengirim objek movie ke DetailMovieActivity
        startActivity(intent)
    }


}