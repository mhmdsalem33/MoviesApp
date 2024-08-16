package com.salem.moviesapp.data.entity.movies_details


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class SpokenLanguageDto(
    @SerializedName("english_name")
    val englishName: String,
    @SerializedName("iso_639_1")
    val iso6391: String,
    @SerializedName("name")
    val name: String
)