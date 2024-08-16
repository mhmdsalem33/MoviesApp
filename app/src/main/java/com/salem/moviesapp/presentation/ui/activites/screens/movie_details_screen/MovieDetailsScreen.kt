package com.salem.moviesapp.presentation.ui.activites.screens.movie_details_screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.salem.moviesapp.presentation.navigations.MovieDetailsScreen


@Composable
fun MovieDetailsScreen( viewModel : MovieDetailsViewModel = hiltViewModel() , args : MovieDetailsScreen ){

    val movieId = args.movieId
    viewModel.getMoviesDetails( movieId )

}