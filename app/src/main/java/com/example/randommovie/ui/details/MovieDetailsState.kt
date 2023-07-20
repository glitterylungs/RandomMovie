package com.example.randommovie.ui.details

import com.example.randommovie.repository.model.Movie

sealed class MovieDetailsState {
    object Loading : MovieDetailsState()
    data class Success(val movie: Movie) : MovieDetailsState()
    data class Error(val errorMessage: String) : MovieDetailsState()
}