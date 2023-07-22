package com.example.randommovie.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.randommovie.ui.details.MovieDetails
import com.example.randommovie.ui.favourite.FavouriteMovies
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationHost(
    navController: NavHostController = rememberAnimatedNavController(),
    startDestination: String = NavigationRoutes.MovieDetails.route
) {
    AnimatedNavHost(navController = navController, startDestination = startDestination) {

        composable(route = NavigationRoutes.MovieDetails.route) {
            MovieDetails(
                navigateToFavourites = { navController.navigate(NavigationRoutes.FavouriteMovies.route) }
            )
        }

        composable(route = NavigationRoutes.FavouriteMovies.route) {
            FavouriteMovies(
                navigateToPreviousScreen = { navController.popBackStack() }
            )
        }
    }
}