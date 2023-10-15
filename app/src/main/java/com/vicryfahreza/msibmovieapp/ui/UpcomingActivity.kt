package com.vicryfahreza.msibmovieapp.ui

import MovieResponse
import NowPlayingAdapter
import NowPlayingListener
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.vicryfahreza.msibmovieapp.R
import com.vicryfahreza.msibmovieapp.api.Network
import com.vicryfahreza.msibmovieapp.databinding.ActivityMainBinding
import com.vicryfahreza.msibmovieapp.databinding.ActivityTopRatedBinding
import com.vicryfahreza.msibmovieapp.databinding.ActivityUpcomingBinding
import kotlinx.coroutines.launch

class UpcomingActivity : AppCompatActivity(), NowPlayingListener {
    private lateinit var binding: ActivityUpcomingBinding
    private lateinit var adapter: NowPlayingAdapter
    private var movieList = mutableListOf<MovieResponse>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpcomingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val rvUpComing = binding.rvUpComing

        adapter = NowPlayingAdapter(movieList, this)

        rvUpComing.layoutManager = GridLayoutManager(this, 2)
        rvUpComing.adapter = adapter

        lifecycleScope.launch {
            val result = Network.getService(this@UpcomingActivity).getUpComing(
                page = 2
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

        binding.btnNowPlaying.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onNowPlayingDetailClick(movieResponse: MovieResponse) {
        TODO("Not yet implemented")
    }
}