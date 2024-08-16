package com.salem.moviesapp.data.entity.movies_details


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class GenreDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)