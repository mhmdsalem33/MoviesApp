package com.salem.moviesapp.data.data_source.remote.api

import com.salem.moviesapp.data.entity.MoviesListDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MoviesApi {

    @GET("movies")
    suspend fun getWatchedMoviesList(
        @Query("language") language : String = "en-US",
        @Query("page") page : Int  ,
        @Query("sort_by") sortBy      : String  = "created_at.asc" ,
        ) :MoviesListDto


}