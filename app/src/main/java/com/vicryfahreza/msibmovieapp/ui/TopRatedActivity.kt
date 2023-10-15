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
import com.vicryfahreza.msibmovieapp.databinding.ActivityTopRatedBinding
import kotlinx.coroutines.launch

class TopRatedActivity : AppCompatActivity(), NowPlayingListener {
    private lateinit var binding: ActivityTopRatedBinding
    private lateinit var adapter: NowPlayingAdapter
    private var movieList = mutableListOf<MovieResponse>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopRatedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val rvTopRated = binding.rvTopRated

        adapter = NowPlayingAdapter(movieList, this)

        rvTopRated.layoutManager = GridLayoutManager(this, 2)
        rvTopRated.adapter = adapter

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
            val intent = Intent(this, UpcomingMovie::class.java)
            startActivity(intent)
        }

    }

    override fun onNowPlayingDetailClick(movieResponse: MovieResponse) {
        TODO("Not yet implemented")
    }
}