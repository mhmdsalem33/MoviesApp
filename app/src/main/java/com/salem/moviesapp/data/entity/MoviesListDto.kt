package com.salem.moviesapp.data.entity


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class MoviesListDto(
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("results")
    val results: List<MoviesResultDto> = emptyList(),
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("total_results")
    val totalResults: Int = 0
)