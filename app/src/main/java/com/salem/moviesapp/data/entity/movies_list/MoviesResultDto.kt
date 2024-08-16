package com.salem.moviesapp.data.entity.movies_list


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class MoviesResultDto(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("original_title")
    val originalTitle: String = "",
    @SerializedName("release_date")
    val releaseDate: String = "",
    @SerializedName("poster_path")
    val posterPath: String = "",
    @SerializedName("overview")
    val overview: String = ""
)