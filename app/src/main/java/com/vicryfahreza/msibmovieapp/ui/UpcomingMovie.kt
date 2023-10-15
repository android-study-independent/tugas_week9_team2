package com.vicryfahreza.msibmovieapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vicryfahreza.msibmovieapp.R
import com.vicryfahreza.msibmovieapp.api.Network
import com.vicryfahreza.msibmovieapp.response.UpcomingMovieResponse
import com.vicryfahreza.msibmovieapp.ui.adapter.UpcomingAdapter
import com.vicryfahreza.msibmovieapp.ui.detail.DetailMovieActivity
import kotlinx.coroutines.launch

class UpcomingMovie : AppCompatActivity() {

    private lateinit var adapter: UpcomingAdapter
    private var listUpcoming = mutableListOf<UpcomingMovieResponse>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upcoming)

        val rvUpcomingMovie = findViewById<RecyclerView>(R.id.rvUpComing)
        rvUpcomingMovie.layoutManager = LinearLayoutManager(this)
        adapter = UpcomingAdapter(listUpcoming)
        rvUpcomingMovie.adapter = adapter

        adapter.onItemClick = {
            val intent = Intent(this@UpcomingMovie, DetailMovieActivity::class.java)
            intent.putExtra("Upcoming", it)
            startActivity(intent)
        }

        lifecycleScope.launch {
            val result = Network.getService(this@UpcomingMovie).getUpComing(
                page = 1
            )

            Log.d("Debug", "total page -> ${result.totalPage}")

            result.results.map {
                Log.d("Debug", "hasil -> ${it.title} - ${it.overview}")
                listUpcoming.add(it)
            }

            adapter.notifyDataSetChanged()
        }



    }
}