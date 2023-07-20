package com.example.randommovie.ui.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetails(
    viewModel: MovieDetailsViewModel = koinViewModel()
) {
    val state by remember { viewModel.movieDetailsState }

    LaunchedEffect(true) {
        viewModel.getRandomMovie()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Random Movie")
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
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = (state as MovieDetailsState.Error).errorMessage)
                }
            }
            is MovieDetailsState.Success -> {
                val movie = (state as MovieDetailsState.Success).movie
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                        .padding(
                            vertical = it.calculateTopPadding(),
                            horizontal = 15.dp
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .clip(RoundedCornerShape(10.dp))
                    ) {
                        AsyncImage(
                            model = movie.primaryImage.url,
                            contentDescription = movie.titleText.text,
                            modifier = Modifier.aspectRatio(2f / 3f),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Text(text = movie.titleText.text)
                    Text(text = movie.releaseYear.year.toString())
                }
            }
        }
    }
}