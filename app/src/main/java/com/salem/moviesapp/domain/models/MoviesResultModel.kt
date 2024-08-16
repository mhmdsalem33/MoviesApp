package com.salem.moviesapp.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity( tableName  = "moviesList")
data class MoviesResultModel(
    @PrimaryKey
    @ColumnInfo("id")
    val id : Int = 0,
    @ColumnInfo("title")
    val title : String = "",
    @ColumnInfo("originalTitle")
    val originalTitle : String = "",
    @ColumnInfo("releaseDate")
    val releaseDate : String = "",
    @ColumnInfo("posterPath")
    val posterPath : String = ""
)


