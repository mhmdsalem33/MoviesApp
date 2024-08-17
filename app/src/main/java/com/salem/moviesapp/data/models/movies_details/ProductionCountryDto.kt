package com.salem.moviesapp.data.models.movies_details


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class ProductionCountryDto(
    @SerializedName("iso_3166_1")
    val iso31661: String,
    @SerializedName("name")
    val name: String
)