package com.example.randommovie.ui.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.randommovie.repository.MovieRepository
import com.example.randommovie.repository.model.Movie
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieDetailsViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    var movie: MutableState<Movie?> = mutableStateOf(null)
        private set

    var isFavourite: MutableState<Boolean> = mutableStateOf(false)

    var errorMessage: MutableState<String?> = mutableStateOf(null)

    fun getRandomMovie() {
        movieRepository.getRandomMovies("most_pop_movies", 1)
            .subscribeOn(Schedulers.io())
            .doOnSuccess { movieList ->
                val movie = movieList.results.firstOrNull()
                if (movie != null) {
                    this@MovieDetailsViewModel.movie.value = movie
                } else {
                    errorMessage.value = "No movie found"
                }
            }
            .doOnError { error ->
                errorMessage.value = error.message ?: "Unknown error occurred"
            }
            .subscribe()
    }
}