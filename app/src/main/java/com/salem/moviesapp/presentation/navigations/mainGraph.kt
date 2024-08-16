package com.salem.moviesapp.presentation.navigations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.salem.moviesapp.presentation.ui.activites.screens.movie_details_screen.MovieDetailsScreen
import com.salem.moviesapp.presentation.ui.activites.screens.movies_screen.MovieListScreen
import kotlinx.serialization.Serializable

fun NavGraphBuilder.mainGraph(
    navController: NavHostController,
) {

    composable<MoviesListScreen>
    {
        MovieListScreen(navController)
    }

    composable<MovieDetailsScreen>
    {
        val args = it.toRoute<MovieDetailsScreen>()
        MovieDetailsScreen( args = args)
    }
}


@Serializable
object  MoviesListScreen
@Serializable
data class MovieDetailsScreen(
    val movieId : Int
)


