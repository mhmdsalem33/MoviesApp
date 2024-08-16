package com.salem.moviesapp.domain.repo

import androidx.paging.PagingSource
import com.salem.moviesapp.core.ResponseState
import com.salem.moviesapp.domain.models.MovieModel
import com.salem.moviesapp.domain.models.MoviesResultModel
import kotlinx.coroutines.flow.Flow

interface MoviesRepo {
    fun getMoviesPagingSource() : PagingSource<Int, MoviesResultModel>
    suspend fun fetchAndStoreMovies(page: Int)
    suspend fun getMovieDetails( movieId : Int ) : Flow<ResponseState<MovieModel>>

}