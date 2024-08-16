package com.salem.moviesapp.domain.repo

import androidx.paging.PagingSource
import com.salem.moviesapp.domain.models.MoviesResultModel

interface MoviesRepo {
    fun getMoviesPagingSource() : PagingSource<Int, MoviesResultModel>
    suspend fun fetchAndStoreMovies(page: Int)

}