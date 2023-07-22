package com.example.randommovie.ui.navigation

sealed class NavigationRoutes(val route: String) {

    object MovieDetails : NavigationRoutes("movie_details")
    object FavouriteMovies : NavigationRoutes("favourite_movies")
}