package com.watchlist.movies.ui.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.watchlist.movies.R
import com.watchlist.movies.ui.Movie

class MovieAdapter(private val clickListener: OnItemClickListener<Movie>) :
    ListAdapter<Movie, MovieAdapter.ViewHolder>(MovieDiffUtil()) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val titleTextView: TextView = view.findViewById(R.id.text_title)
        private val ratingTextView: TextView = view.findViewById(R.id.text_rating)
        private val posterImageView: ImageView = view.findViewById(R.id.image_poster)

        fun bind(item: Movie) {
            titleTextView.text = item.title
            ratingTextView.text = item.ratingFormatted
            posterImageView.loadPoster(item.posterLink)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener { clickListener.onItemClick(item) }
    }

    private class MovieDiffUtil : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem.title == newItem.title &&
                    oldItem.rating == newItem.rating &&
                    oldItem.posterLink == newItem.posterLink

    }
}

