package com.example.randommovie.ui.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randommovie.repository.FavouriteMovieRepository
import com.example.randommovie.repository.model.FavouriteMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class FavouriteMoviesViewModel(
    private val favouriteMovieRepository: FavouriteMovieRepository
) : ViewModel() {

    var favouriteMovies: MutableStateFlow<List<FavouriteMovie>> = MutableStateFlow(emptyList())
        private set

    fun getFavouriteMovies() =
        viewModelScope.launch(Dispatchers.IO) {
            favouriteMovieRepository.getFavouriteMovies().collect { movies ->
                favouriteMovies.value = movies
            }
        }

    fun deleteMovie(id: Int) =
        viewModelScope.launch(Dispatchers.IO) {
            favouriteMovieRepository.deleteFavouriteMovie(id)
        }
}