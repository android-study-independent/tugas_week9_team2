import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vicryfahreza.msibmovieapp.R
import com.vicryfahreza.msibmovieapp.ui.adapter.DetailMovieActivity


interface NowPlayingListener {
    fun onNowPlayingDetailClick(movieResponse: MovieResponse)
}

class NowPlayingAdapter(private val movieList: List<MovieResponse>, private val listener: NowPlayingListener) : RecyclerView.Adapter<NowPlayingAdapter.NowPlayingHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.now_playing_item, parent, false)
        return NowPlayingHolder(view)
    }

    inner class NowPlayingHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(movie: MovieResponse) {
            val ivMovie = view.findViewById<ImageView>(R.id.ivMovie)
            val tvTitle = view.findViewById<TextView>(R.id.tvTitle)

            val context: Context = view.context
            val urlImage = "https://image.tmdb.org/t/p/w500/${movie.posterPath}"

            tvTitle.text = movie.title
            Picasso.get().load(urlImage).into(ivMovie)

            ivMovie.setOnClickListener {
                // Di sini, Anda akan memulai DetailMovieActivity saat ivMovie diklik.
                val intent = Intent(context, DetailMovieActivity::class.java)
                intent.putExtra("movie", movie) // Mengirim objek movie ke DetailMovieActivity
                context.startActivity(intent)
            }
        }
    }

    override fun onBindViewHolder(holder: NowPlayingHolder, position: Int) {
        holder.bindView(movieList[position])
    }

    override fun getItemCount(): Int = movieList.size
}
