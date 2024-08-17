package com.salem.moviesapp.presentation.ui.activites.screens.movie_details_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.salem.moviesapp.R
import com.salem.moviesapp.core.ResponseState
import com.salem.moviesapp.domain.models.MovieModel
import com.salem.moviesapp.presentation.common.Constants
import com.salem.moviesapp.presentation.navigations.MovieDetailsScreen
import com.salem.moviesapp.presentation.widgets.RoundImage
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import com.salem.moviesapp.domain.models.GenreModel
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.Alignment


@Composable
fun MovieDetailsScreen(
    viewModel: MovieDetailsViewModel = hiltViewModel(),
    args: MovieDetailsScreen
) {

    val movieId = args.movieId

    LaunchedEffect(movieId) {
        viewModel.getMoviesDetails(movieId)
    }

    val movieDetailsState by viewModel.movieDetails.collectAsStateWithLifecycle()

    MovieDetailsItem(movieDetailsState)


}


@Composable
fun MovieDetailsItem(movieDetails: ResponseState<MovieModel>) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
        ) {
            when (movieDetails) {
                is ResponseState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is ResponseState.Success -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                    ) {
                        RoundImage(
                            url = "${Constants.BASE_IMAGE_URL}${movieDetails.data?.posterPath}",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(160.dp)
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(text = movieDetails.data?.title ?: "")

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(text = movieDetails.data?.releaseDate ?: "")

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(text = movieDetails.data?.overview ?: "")

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(text = stringResource(id = R.string.genres))

                        movieDetails.data?.genres?.let { genres ->
                            GenreList(genres)
                        }
                    }
                }
                is ResponseState.Error -> {
                    Text(
                        text      =  stringResource( id = R.string.check_your_internet ),
                        modifier  = Modifier.align(Alignment.Center),
                        color     = Color.Red
                    )
                }
                else -> Unit
            }
        }
    }
}


@Composable
fun GenreItem(genre: String) {
    Box(
        modifier = Modifier
            .background(color = Color.LightGray, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(text = genre, color = Color.Black)
    }
}


@Composable
fun GenreList(genres: List<GenreModel>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(end = 16.dp, top = 8.dp , bottom = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(genres) { genre ->
            GenreItem(genre = genre.name)
        }
    }
}