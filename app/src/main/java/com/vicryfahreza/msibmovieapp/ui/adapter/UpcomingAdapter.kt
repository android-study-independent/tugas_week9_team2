package com.vicryfahreza.msibmovieapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vicryfahreza.msibmovieapp.R
import com.squareup.picasso.Picasso
import com.vicryfahreza.msibmovieapp.response.UpcomingMovieResponse
import java.nio.file.Path

class UpcomingAdapter (private val listUpcoming: List<UpcomingMovieResponse>) : RecyclerView.Adapter<UpcomingAdapter.UpcomingMovieHolder>() {

    var onItemClick : ((UpcomingMovieResponse) -> Unit)? = null

    inner class UpcomingMovieHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bindView(upcoming : UpcomingMovieResponse) {

            val imgUpcoming = view.findViewById<ImageView>(R.id.imgUpComing)
            val titleUpcoming = view.findViewById<TextView>(R.id.txtTitleMovie)
            val ratingUpcomingMovie = view.findViewById<TextView>(R.id.txtRating)
            val releaseDateUpcomingMovie = view.findViewById<TextView>(R.id.txtDate)
            val languageUpcomingMovie = view.findViewById<TextView>(R.id.txtLanguage)

            titleUpcoming.text = upcoming.title
            ratingUpcomingMovie.text = upcoming.voteAverage.toString()
            releaseDateUpcomingMovie.text = upcoming.releaseDate
            languageUpcomingMovie.text = upcoming.originalLanguage

            val path = buildPosterUpcoming(upcoming.posterPath)
            Picasso.get().load(path).into(imgUpcoming)


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingMovieHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_upcoming, parent, false)
        return UpcomingMovieHolder(view)
    }

    override fun getItemCount(): Int {
        return listUpcoming.size
    }

    override fun onBindViewHolder(holder: UpcomingMovieHolder, position: Int) {

        val itemUpcomingMovie = listUpcoming[position]
        holder.bindView(listUpcoming[position])

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(itemUpcomingMovie)
        }
    }

    private fun buildPosterUpcoming(posterPath: String?): String {
        return "https://image.tmdb.org/t/p/w500/$posterPath"
    }

}