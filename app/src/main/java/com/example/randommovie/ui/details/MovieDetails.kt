package com.example.randommovie.ui.details

import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.randommovie.repository.model.Movie

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetails(
    viewModel: MovieDetailsViewModel = koinViewModel(),
    navigateToFavourites: () -> Unit
) {
    val state by remember { viewModel.movieDetailsState }
    val isFavourite by remember { viewModel.isFavourite }
    var isScreenSwipedLeft by remember { mutableStateOf(false) }

    LaunchedEffect(true) {
        viewModel.getRandomMovie()
    }

    LaunchedEffect(isScreenSwipedLeft) {
        if (isScreenSwipedLeft) {
            isScreenSwipedLeft = false
            navigateToFavourites()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "Random Movie",
                    style = MaterialTheme.typography.headlineMedium
                )
            })
        }
    ) {
        when (state) {
            is MovieDetailsState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is MovieDetailsState.Error -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = (state as MovieDetailsState.Error).errorMessage,
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )
                    Refresh(
                        contentDescription = "Refresh",
                        onClick = { viewModel.getRandomMovie() }
                    )
                }
            }
            is MovieDetailsState.Success -> {
                val movie = (state as MovieDetailsState.Success).movie

                MovieDetailsSuccessContent(
                    movie = movie,
                    isFavourite = isFavourite,
                    onScreenSwipedLeft = { isScreenSwipedLeft = true },
                    onAddToFavourites = { title -> viewModel.addMovieToFavourites(title) },
                    onGetRandomMovie = { viewModel.getRandomMovie() },
                    paddingValues = it
                )
            }
        }
    }
}

@Composable
fun MovieDetailsSuccessContent(
    movie: Movie,
    isFavourite: Boolean,
    onScreenSwipedLeft: () -> Unit,
    onAddToFavourites: (String) -> Unit,
    onGetRandomMovie: () -> Unit,
    paddingValues: PaddingValues,

    ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .pointerInput(Unit) {
                detectHorizontalDragGestures { _, dragAmount ->
                    if (dragAmount < 0) {
                        onScreenSwipedLeft()
                    }
                }
            }
            .padding(
                start = 15.dp,
                top = paddingValues.calculateTopPadding(),
                end = 15.dp,
                bottom = 10.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .clip(RoundedCornerShape(10.dp))
        ) {
            movie.primaryImage?.url?.let { url ->
                AsyncImage(
                    model = url,
                    contentDescription = movie.titleText?.text,
                    modifier = Modifier.aspectRatio(2f / 3f),
                    contentScale = ContentScale.Crop
                )
            }
        }

        Spacer(modifier = Modifier.padding(25.dp))

        movie.titleText?.text?.let { title ->
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.padding(10.dp))

        movie.releaseYear?.year?.let { year ->
            Text(
                text = year.toString(),
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.weight(1.0f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Refresh(
                contentDescription = "Draw new movie",
                onClick = onGetRandomMovie
            )

            movie.titleText?.text?.let { title ->
                IconButton(
                    onClick = { onAddToFavourites(title) },
                    enabled = !isFavourite
                ) {
                    if (isFavourite) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "Favourite",
                            modifier = Modifier.size(50.dp),
                            tint = Color.Red
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Outlined.FavoriteBorder,
                            contentDescription = "Not favourite",
                            modifier = Modifier.size(50.dp),
                            tint = Color.Red
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Refresh(
    contentDescription: String,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Default.Refresh,
            contentDescription = contentDescription,
            modifier = Modifier.size(50.dp)
        )
    }
}