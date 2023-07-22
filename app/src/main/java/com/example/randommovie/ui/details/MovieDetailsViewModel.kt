package com.example.randommovie.ui.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randommovie.repository.FavouriteMovieRepository
import com.example.randommovie.repository.MovieRepository
import com.example.randommovie.repository.model.FavouriteMovie
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val movieRepository: MovieRepository,
    private val favouriteMovieRepository: FavouriteMovieRepository
) : ViewModel() {

    var movieDetailsState: MutableState<MovieDetailsState> =
        mutableStateOf(MovieDetailsState.Loading)
        private set

    var isFavourite: MutableState<Boolean> = mutableStateOf(false)
        private set

    private var randomMovieDisposable: Disposable? = null

    fun getRandomMovie() {
        randomMovieDisposable = movieRepository.getRandomMovies("most_pop_movies", 1)
            .subscribeOn(Schedulers.io())
            .subscribe({ movieList ->
                val movie = movieList.results?.firstOrNull()
                if (movie != null) {
                    movieDetailsState.value = MovieDetailsState.Success(movie)
                    movie.titleText?.text?.let { checkIsMovieFavourite(it) }
                } else {
                    movieDetailsState.value = MovieDetailsState.Error("No movie found")
                }
            }, { error ->
                movieDetailsState.value =
                    MovieDetailsState.Error(error.message ?: "Unknown error occurred")
            })
    }

    fun addMovieToFavourites(title: String) {
        val newFavouriteMovie = FavouriteMovie(
            id = DEFAULT_ID,
            title = title
        )

        isFavourite.value = true

        viewModelScope.launch(Dispatchers.IO) {
            favouriteMovieRepository.addFavouriteMovie(newFavouriteMovie)
        }
    }

    private fun checkIsMovieFavourite(title: String) =
        viewModelScope.launch(Dispatchers.IO) {
            val isMovieFavourite = favouriteMovieRepository.isMovieFavourite(title)
            isFavourite.value = isMovieFavourite
            println(isFavourite.value)
        }

    override fun onCleared() {
        super.onCleared()
        randomMovieDisposable?.dispose() // Dispose of the subscription when ViewModel is cleared
    }

    companion object {
        private const val DEFAULT_ID = 0
    }
}