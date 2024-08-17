package com.salem.moviesapp.presentation.ui.activites.screens.movie_details_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salem.moviesapp.core.ResponseState
import com.salem.moviesapp.domain.models.MovieModel
import com.salem.moviesapp.domain.usecases.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {



    private val _movieDetails = MutableStateFlow<ResponseState<MovieModel>>(ResponseState.Loading())
    val movieDetails =  _movieDetails.asStateFlow()


    private var movieJob: Job? = null
    fun getMoviesDetails(movieId: Int) {
        movieJob?.cancel()
        movieJob = viewModelScope.launch(Dispatchers.IO) {
            getMovieDetailsUseCase(movieId).collect {
                _movieDetails.emit(it)
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        movieJob?.cancel()
    }


}
