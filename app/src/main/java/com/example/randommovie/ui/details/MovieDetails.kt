package com.example.randommovie.ui.details

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.getValue

@Composable
fun MovieDetails(
    viewModel: MovieDetailsViewModel = koinViewModel()
) {
    val movie by remember { viewModel.movie }
    val errorMessage by remember { viewModel.errorMessage }

    LaunchedEffect(true) {
        viewModel.getRandomMovie()
    }

    movie?.titleText?.let { Text(text = it.text) }
    errorMessage?.let { Text(text = it) }
}