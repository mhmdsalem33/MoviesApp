package com.salem.moviesapp.presentation.ui.activites.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.salem.moviesapp.presentation.ui.activites.HomeViewModel
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.items
import kotlinx.coroutines.delay

@Composable
fun MovieListScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    paddingValues: PaddingValues
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

    Box(modifier = Modifier.padding(paddingValues)) {
        LazyColumn(state = lazyColumnState) {
            items(moviePagingFlow) { movie ->
                movie?.let {
                    Text(text = it.title, modifier = Modifier.height(100.dp))
                }
            }

            if (showLoadingIndicator.value || moviePagingFlow.loadState.append is LoadState.Loading) {
                item {
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
