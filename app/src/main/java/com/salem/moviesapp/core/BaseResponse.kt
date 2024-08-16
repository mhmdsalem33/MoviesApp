package com.salem.moviesapp.core

import androidx.annotation.Keep

@Keep
open class BaseResponse(
    val success : Boolean = false,
    val statusCode : Int = 0,
    val statusMessage : String = ""
)
