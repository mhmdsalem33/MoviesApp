package com.salem.moviesapp.data.entity.movies_details


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class ProductionCompanyDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo_path")
    val logoPath: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("origin_country")
    val originCountry: String
)