package com.watchlist.movies.ui.favorites

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.watchlist.movies.R
import com.watchlist.movies.ui.Movie
import com.watchlist.movies.ui.MovieAdapter
import com.watchlist.movies.ui.OnItemClickListener
import kotlinx.coroutines.launch

class FavoritesFragment : Fragment(R.layout.fragment_favorites), OnItemClickListener<Movie> {

    private val viewModel by viewModels<FavoritesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_movies)
        val adapter = MovieAdapter(this)
        recyclerView.adapter = adapter


        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.movies.collect {
                    adapter.submitList(it)
                }
            }

        }
    }

    override fun onItemClick(item: Movie) {
        val id = item.id
        // TODO: replace with safeargs
        val bundle = bundleOf("id" to id)
        findNavController().navigate(R.id.action_favorites_to_movie_detail, bundle)
    }
}