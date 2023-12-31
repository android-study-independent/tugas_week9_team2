package com.vicryfahreza.msibmovieapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.vicryfahreza.msibmovieapp.R
import com.vicryfahreza.msibmovieapp.api.Network
import com.vicryfahreza.msibmovieapp.databinding.ActivityUpcomingBinding
import com.vicryfahreza.msibmovieapp.firebasedemo.SignInActivity
import com.vicryfahreza.msibmovieapp.response.UpcomingMovieResponse
import com.vicryfahreza.msibmovieapp.ui.adapter.UpcomingAdapter
import com.vicryfahreza.msibmovieapp.ui.detail.DetailMovieActivity
import kotlinx.coroutines.launch

class UpcomingActivity : AppCompatActivity() {

    private lateinit var adapter: UpcomingAdapter
    private var listUpcoming = mutableListOf<UpcomingMovieResponse>()
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upcoming)
        val binding = ActivityUpcomingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val rvUpComing = findViewById<RecyclerView>(R.id.rvUpComing)
        rvUpComing.layoutManager = LinearLayoutManager(this)
        adapter = UpcomingAdapter(listUpcoming)
        rvUpComing.adapter = adapter

        auth = Firebase.auth
        val firebaseUser = auth.currentUser

        adapter.onItemClick = {
            val intent = Intent(this, DetailMovieActivity::class.java)
            intent.putExtra("Upcoming", it)
            startActivity(intent)
        }

        binding.btnNowPlaying.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnPopular.setOnClickListener {
            val intent = Intent(this, PopularActivity::class.java)
            startActivity(intent)
        }

        binding.btnTopRated.setOnClickListener {
            val intent = Intent(this, TopRatedActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogout.setOnClickListener {
            signOut()
        }

        lifecycleScope.launch {
            val result = Network.getService(this@UpcomingActivity).getUpcoming(
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

    private fun signOut() {
        auth.signOut()
        startActivity(Intent(this, SignInActivity::class.java))
        finish()
    }
    fun onNowPlayingDetailClick(movie: UpcomingMovieResponse) {

        // Di sini, Anda akan memulai DetailMovieActivity saat ivMovie diklik.
        val intent = Intent(this, DetailMovieActivity::class.java)
        intent.putExtra("movie", movie) // Mengirim objek movie ke DetailMovieActivity
        startActivity(intent)
    }
}
