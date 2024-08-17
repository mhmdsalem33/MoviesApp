package com.salem.moviesapp.data.mappers

import com.salem.moviesapp.data.models.movies_details.GenreDto
import com.salem.moviesapp.data.models.movies_details.MovieDetailsDto
import com.salem.moviesapp.data.models.movies_list.MoviesResultDto
import com.salem.moviesapp.domain.models.GenreModel
import com.salem.moviesapp.domain.models.MovieModel
import com.salem.moviesapp.domain.models.MoviesResultModel

fun MoviesResultDto.toMoviesResultEntity(): MoviesResultModel {
    return MoviesResultModel(
        id = this.id,
        title = this.title,
        originalTitle = this.originalTitle,
        releaseDate = this.releaseDate,
        posterPath = this.posterPath,
    )
}


fun MovieDetailsDto.toMovieModel() : MovieModel{
    return MovieModel(
        releaseDate = this.releaseDate ,
        title = this.title ,
        posterPath =  this.posterPath ,
        overview = this.overview,
        genres = this.genres.map { it .toGenreModel()}
    )
}


fun GenreDto.toGenreModel() : GenreModel{
    return GenreModel(
        id = this.id,
        name = this.name
    )
}