package com.salem.moviesapp.domain.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.salem.moviesapp.data.entity.movies_details.GenreDto

data class MovieModel(
    val releaseDate : String = "",
    val title : String = "",
    val posterPath : String = "",
    val overview : String = "",
    val genres: List<GenreModel> =  emptyList(),

    )


