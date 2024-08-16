package com.salem.moviesapp.presentation.ui.activites.screens.movies_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.salem.moviesapp.domain.usecases.GetMoviesUseCase
import com.salem.moviesapp.domain.usecases.RefreshMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val refreshMoviesUseCase: RefreshMoviesUseCase

) : ViewModel(){


    private var currentPage = 1

    init {
        viewModelScope.launch {
            refreshMoviesUseCase(1)
        }
    }

    val movieFlow = Pager(PagingConfig(pageSize = 20)) { getMoviesUseCase()
    }.flow.cachedIn(viewModelScope)


    fun loadNextPage() {
        viewModelScope.launch {
            currentPage++
            refreshMoviesUseCase(currentPage)
        }
    }


}