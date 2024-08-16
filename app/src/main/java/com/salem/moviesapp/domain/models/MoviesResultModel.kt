package com.salem.moviesapp.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity( tableName  = "moviesList")
data class MoviesResultModel(
    @PrimaryKey
    val id : Int = 0,
    val title : String = "",
    val originalTitle : String = "",
    val releaseDate : String = "",
    val posterPath : String = ""

)


