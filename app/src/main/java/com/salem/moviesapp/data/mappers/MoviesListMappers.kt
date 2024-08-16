package com.salem.moviesapp.data.mappers

import com.salem.moviesapp.data.entity.MoviesResultDto
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