package com.example.randommovie.ui.favourite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteMovies(
    viewModel: FavouriteMoviesViewModel = koinViewModel(),
    navigateToPreviousScreen: () -> Unit
) {
    val favouriteMovies by viewModel.favouriteMovies.collectAsState(initial = emptyList())

    LaunchedEffect(true) {
        viewModel.getFavouriteMovies()
    }

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
        LazyColumn(
            modifier = Modifier.padding(
                start = 12.dp,
                top = it.calculateTopPadding(),
                end = 12.dp,
                bottom = it.calculateBottomPadding()
            )
        ) {
            items(favouriteMovies) { movie ->
                FavouriteMoviesListItem(
                    title = movie.title,
                    deleteFavourite = { viewModel.deleteMovie(movie.id) }
                )
            }
        }
    }
}

@Composable
fun FavouriteMoviesListItem(
    title: String,
    deleteFavourite: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.weight(1f)
        )

        IconButton(onClick = deleteFavourite) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorite",
                modifier = Modifier.size(50.dp),
                tint = Color.Red
            )
        }
    }
    Divider()
}