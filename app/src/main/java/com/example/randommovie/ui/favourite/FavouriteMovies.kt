package com.example.randommovie.ui.favourite

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteMovies(
    viewModel: FavouriteMoviesViewModel = koinViewModel(),
    navigateToPreviousScreen: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Favourite movies", style = MaterialTheme.typography.headlineMedium)
                },
                navigationIcon = {
                    IconButton(onClick = navigateToPreviousScreen) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Return"
                        )
                    }
                }
            )
        }) {
    }
}