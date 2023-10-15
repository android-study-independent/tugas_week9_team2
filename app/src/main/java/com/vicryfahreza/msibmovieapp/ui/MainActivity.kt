package com.vicryfahreza.msibmovieapp.ui

import MovieResponse
import NowPlayingAdapter
import NowPlayingListener
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.vicryfahreza.msibmovieapp.api.Network
import com.vicryfahreza.msibmovieapp.databinding.ActivityMainBinding
import com.vicryfahreza.msibmovieapp.ui.detail.DetailMovieActivity
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), NowPlayingListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NowPlayingAdapter
    private var movieList = mutableListOf<MovieResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val rvMain = binding.rvMain

        adapter = NowPlayingAdapter(movieList, this)

        rvMain.layoutManager = GridLayoutManager(this, 2)
        rvMain.adapter = adapter

        lifecycleScope.launch {
            val result = Network.getService(this@MainActivity).getNowPlaying(
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

        binding.btnPopular.setOnClickListener {
            val intent = Intent(this, PopularActivity::class.java)
            startActivity(intent)
        }

        binding.btnUpComing.setOnClickListener {
            val intent = Intent(this, UpcomingMovie::class.java)
            startActivity(intent)
        }

    }

    override fun onNowPlayingDetailClick(movie: MovieResponse) {
        // Di sini, Anda akan memulai DetailMovieActivity saat ivMovie diklik.
        val intent = Intent(this, DetailMovieActivity::class.java)
        intent.putExtra("movie", movie) // Mengirim objek movie ke DetailMovieActivity
        startActivity(intent)
    }
}
