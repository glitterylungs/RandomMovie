package com.example.randommovie.ui.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.randommovie.repository.MovieRepository
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieDetailsViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    var movieDetailsState: MutableState<MovieDetailsState> =
        mutableStateOf(MovieDetailsState.Loading)
        private set

    private var randomMovieDisposable: Disposable? = null

    fun getRandomMovie() {
        randomMovieDisposable = movieRepository.getRandomMovies("most_pop_movies", 1)
            .subscribeOn(Schedulers.io())
            .subscribe({ movieList ->
                val movie = movieList.results?.firstOrNull()
                if (movie != null) {
                    movieDetailsState.value = MovieDetailsState.Success(movie)
                    println(movie.primaryImage?.url)
                } else {
                    movieDetailsState.value = MovieDetailsState.Error("No movie found")
                }
            }, { error ->
                movieDetailsState.value =
                    MovieDetailsState.Error(error.message ?: "Unknown error occurred")
            })
    }

    override fun onCleared() {
        super.onCleared()
        randomMovieDisposable?.dispose() // Dispose of the subscription when ViewModel is cleared
    }
}