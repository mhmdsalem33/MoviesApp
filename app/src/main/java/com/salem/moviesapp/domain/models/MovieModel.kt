package com.salem.moviesapp.domain.models

data class MovieModel(
    val releaseDate : String = "",
    val title : String = "",
    val posterPath : String = "",
    val overview : String = "",
    val genres: List<GenreModel> =  emptyList(),
    )


