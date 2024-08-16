package com.salem.moviesapp.presentation.ui.activites.screens.movie_details_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salem.moviesapp.core.ResponseState
import com.salem.moviesapp.domain.usecases.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {


    private var movieJob: Job? = null
    fun getMoviesDetails(movieId: Int) {
        movieJob?.cancel()
        movieJob = viewModelScope.launch(Dispatchers.IO) {
            getMovieDetailsUseCase(movieId).collect {
                when (it) {
                    is ResponseState.Loading -> {
                        Log.e("testApp", "get movie details is loading")
                    }
                    is ResponseState.Success -> {
                        Log.e("testApp", "get movie details is success ${it.data}")
                    }
                    is ResponseState.Error -> {
                        Log.e("testApp", "get movie details is error ${it.message}")
                    }
                    else -> Unit
                }
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        movieJob?.cancel()
    }


}
