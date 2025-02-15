package com.watchlist.movies.ui.home

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.watchlist.movies.R
import com.watchlist.movies.ui.Movie
import com.watchlist.movies.ui.MovieAdapter
import com.watchlist.movies.ui.OnItemClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), OnItemClickListener<Movie> {

    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_movies)
        val adapter = MovieAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        val progressBar = view.findViewById<ProgressBar>(R.id.progress_bar)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    adapter.submitList(it.movies)
                    progressBar.isVisible = it.isLoading
                    if (it.errorMessage != null) {
                        showErrorMessage(it.errorMessage)
                    }
                }
            }

        }
    }

    override fun onItemClick(item: Movie) {
        val action = HomeFragmentDirections.actionHomeToMovieDetail(item.id)
        findNavController().navigate(action)
    }

    private fun showErrorMessage(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
        viewModel.markErrorMessageAsSeen()
    }
}