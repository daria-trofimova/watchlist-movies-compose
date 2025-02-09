package com.watchlist.movies.ui.movie.detail

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.MaterialToolbar
import com.watchlist.movies.R
import kotlinx.coroutines.launch

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private val viewModel by viewModels<MovieDetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: replace with safeargs
        val id = savedInstanceState?.getString("id")

        setupToolbar()
        val titleTextView = view.findViewById<TextView>(R.id.text_title)
        val ratingTextView = view.findViewById<TextView>(R.id.text_rating)
        val favoriteButton = view.findViewById<ImageButton>(R.id.button_favorite)
        favoriteButton.setOnClickListener {
            viewModel.toggleFavorite()
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.movie.collect { movie ->
                    titleTextView.text = movie.title
                    ratingTextView.text = movie.rating.toString()
                    val favoriteButtonRes =
                        if (movie.isFavorite) R.drawable.favorite_fill else R.drawable.favorite
                    favoriteButton.setImageResource(favoriteButtonRes)
                }
            }
        }
    }

    private fun setupToolbar() {
        val toolbar = requireView().findViewById<MaterialToolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }
}