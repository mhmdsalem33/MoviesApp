package com.salem.moviesapp.data.repo

import android.util.Log
import androidx.paging.PagingSource
import com.salem.moviesapp.core.ResponseState
import com.salem.moviesapp.data.data_source.local.database.MoviesDatabase
import com.salem.moviesapp.data.data_source.remote.api.MoviesApi
import com.salem.moviesapp.data.extentions.safeApiCall
import com.salem.moviesapp.data.mappers.toMovieModel
import com.salem.moviesapp.data.mappers.toMoviesResultEntity
import com.salem.moviesapp.domain.models.MovieModel
import com.salem.moviesapp.domain.models.MoviesResultModel
import com.salem.moviesapp.domain.repo.MoviesRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesApi: MoviesApi,
    private val moviesDatabase: MoviesDatabase
) : MoviesRepo {
    override fun getMoviesPagingSource(): PagingSource<Int, MoviesResultModel> =  moviesDatabase.dao.pagingSource()

    override suspend fun fetchAndStoreMovies(page: Int) {
        try {
            val movies = moviesApi.getWatchedMoviesList(page = page)
            val movieEntities = movies.results.map { it.toMoviesResultEntity() }
            moviesDatabase.dao.upsertAll(movieEntities)
        } catch (t: Throwable) {
            Log.e("testApp", t.message ?: "")
        }
    }

    override suspend fun getMovieDetails(movieId: Int): Flow<ResponseState<MovieModel>>  = flow {
        try {
            emit(ResponseState.Loading())
            val remoteResponse = safeApiCall(
                apiCall = { moviesApi.getMovieDetails(id = movieId ) },
                mapper =  { it.toMovieModel() }
            )
            emit(remoteResponse)
        }catch ( t : Throwable ){
            Log.e("testApp" , t.message ?: "")
        }
    }
}
