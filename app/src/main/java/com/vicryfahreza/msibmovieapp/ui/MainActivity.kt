package com.vicryfahreza.msibmovieapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.vicryfahreza.msibmovieapp.api.Network
import com.vicryfahreza.msibmovieapp.databinding.ActivityMainBinding
import com.vicryfahreza.msibmovieapp.response.MovieResponse
import com.vicryfahreza.msibmovieapp.ui.adapter.NowPlayingAdapter
import com.vicryfahreza.msibmovieapp.ui.adapter.NowPlayingListener
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

        adapter = NowPlayingAdapter(movieList, object : NowPlayingListener {
            override fun onNowPlayingDetailClick(movieResponse: MovieResponse) {
                Toast.makeText(this@MainActivity, "test", Toast.LENGTH_SHORT).show()
            }
        })

        adapter = NowPlayingAdapter(movieList, this)

        rvMain.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
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

    }


    override fun onNowPlayingDetailClick(movieResponse: MovieResponse) {
        Toast.makeText(this, "person ${movieResponse.title}", Toast.LENGTH_SHORT).show()
    }

}