package com.salem.moviesapp.data.data_source.remote.api

import com.salem.moviesapp.data.entity.movies_details.MovieDetailsDto
import com.salem.moviesapp.data.entity.movies_list.MoviesListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET("account/550/watchlist/movies")
    suspend fun getWatchedMoviesList(
        @Query("language") language : String = "en-US",
        @Query("page") page : Int  ,
        @Query("sort_by") sortBy : String  = "created_at.asc" ,
        ):MoviesListDto

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") id : Int ) :Response<MovieDetailsDto>




}