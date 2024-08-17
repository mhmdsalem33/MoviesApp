package com.salem.moviesapp.presentation.ui.activites.screens.movies_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.items
import com.salem.moviesapp.domain.models.MoviesResultModel
import com.salem.moviesapp.presentation.common.Constants.BASE_IMAGE_URL
import com.salem.moviesapp.presentation.navigations.MovieDetailsScreen
import com.salem.moviesapp.presentation.widgets.RoundImage
import kotlinx.coroutines.delay

@Composable
fun MovieListScreen(
    nacController : NavHostController,
    viewModel: MoviesViewModel = hiltViewModel(),
) {


    val moviePagingFlow = viewModel.movieFlow.collectAsLazyPagingItems()
    val lazyColumnState = rememberLazyListState()

    val hasScrolled = remember { mutableStateOf(false) }

    val isLastItemVisible by remember {
        derivedStateOf {
            val layoutInfo = lazyColumnState.layoutInfo
            val totalItems = layoutInfo.totalItemsCount
            val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()
            hasScrolled.value && lastVisibleItem?.index == totalItems - 1
        }
    }

    val showLoadingIndicator = remember { mutableStateOf(false) }



    Scaffold(modifier = Modifier.fillMaxSize().background(Color.White)) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            LazyColumn(state = lazyColumnState) {
                items(moviePagingFlow) { movie ->
                    movie?.let {
                        MoviesItem(it , nacController)
                    }
                }

                if (showLoadingIndicator.value || moviePagingFlow.loadState.append is LoadState.Loading) {
                    item {
                        ProgressIndicator()
                    }
                }
            }
        }

    }


    LaunchedEffect(lazyColumnState.firstVisibleItemIndex) {
        if (lazyColumnState.firstVisibleItemIndex > 0) {
            hasScrolled.value = true
        }
    }

    LaunchedEffect(isLastItemVisible) {
        if (isLastItemVisible) {
            showLoadingIndicator.value = true
            delay(2000)
            viewModel.loadNextPage()
            showLoadingIndicator.value = false
        }
    }
}


@Composable
fun MoviesItem( moviesResultModel: MoviesResultModel , navController: NavHostController ) {

    val posterPath = moviesResultModel.posterPath
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 5.dp, start = 5.dp, end = 5.dp)
            .clickable {
                navController.navigate(MovieDetailsScreen( movieId = moviesResultModel.id ))
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        RoundImage("$BASE_IMAGE_URL${posterPath}" , modifier = Modifier.width(100.dp).height(100.dp))

        Spacer(modifier = Modifier.width(20.dp))

        Column {
            Text(text = moviesResultModel.title)
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = moviesResultModel.releaseDate)
        }
    }
}


@Composable
fun ProgressIndicator() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .height(40.dp)
                .width(40.dp)
        )
    }
}