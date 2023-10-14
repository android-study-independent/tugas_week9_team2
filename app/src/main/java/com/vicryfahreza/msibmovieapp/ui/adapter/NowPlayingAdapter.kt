package com.vicryfahreza.msibmovieapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vicryfahreza.msibmovieapp.R
import com.vicryfahreza.msibmovieapp.response.MovieResponse

interface NowPlayingListener {
    fun onNowPlayingDetailClick(movieResponse: MovieResponse)
}

class NowPlayingAdapter(private val movieList: List<MovieResponse>, private val listener: NowPlayingListener): RecyclerView.Adapter<NowPlayingAdapter.NowPlayingHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NowPlayingHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.now_playing_item, parent, false)
        return NowPlayingHolder(view)
    }

    inner class NowPlayingHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(movie: MovieResponse) {
            val ivMovie = view.findViewById<ImageView>(R.id.ivMovie)
            val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
            val urlImage = "https://image.tmdb.org/t/p/w500/${movie.posterPath}"

            tvTitle.text = movie.title
            Picasso.get().load(urlImage).into(ivMovie)

        }
    }

    override fun onBindViewHolder(holder: NowPlayingHolder, position: Int) {
        holder.bindView(movieList[position])
    }

    override fun getItemCount(): Int = movieList.size


}