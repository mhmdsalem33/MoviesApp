package com.salem.moviesapp.data.entity.movies_details


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class BelongsToCollectionDto(
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("poster_path")
    val posterPath: String
)