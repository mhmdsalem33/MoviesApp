package com.salem.moviesapp.presentation.navigations

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun NestedNavGraph( ) {
    val navController = rememberNavController()
    NavHost(
        navController = navController  ,
        startDestination =  MoviesListScreen,
        exitTransition = {  ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }
    ) {
        mainGraph(navController)
    }
}
