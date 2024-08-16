package com.salem.moviesapp.data.extentions

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.salem.moviesapp.core.BaseResponse
import com.salem.moviesapp.core.ResponseState
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.File
import java.io.IOException


fun File.createMultiPartImage(key: String): MultipartBody.Part? {
    return if (this.exists()) {
        val mediaType = this.asRequestBody("*/*".toMediaTypeOrNull())
        MultipartBody.Part.createFormData(key, this.name, mediaType)
    } else {
        null
    }
}

fun File.createMultiPart(
    key: String,
    mediaType: String = "application/octet-stream"
): MultipartBody.Part? {
    return if (this.exists()) {
        val requestBody = this.asRequestBody(mediaType.toMediaTypeOrNull())
        MultipartBody.Part.createFormData(key, this.name, requestBody)
    } else {
        null
    }
}


fun File.createMultiPartAudio(key: String): MultipartBody.Part? {
    return if (this.exists()) {
        val mediaType = "audio/*".toMediaTypeOrNull()
        val requestBody = this.asRequestBody(mediaType)
        MultipartBody.Part.createFormData(key, this.name, requestBody)
    } else {
        null
    }
}

fun String?.createRequestBody(): RequestBody? {
    return this?.toRequestBody("text/plain".toMediaTypeOrNull())
}


fun convertToMap(requestBody: MultipartBody): Map<String, RequestBody> {
    val params = mutableMapOf<String, RequestBody>()
    requestBody.parts.forEach { part ->
        params[part.headers?.get("Content-Disposition")
            ?.replace("form-data; name=\"", "")?.replace("\"", "")
            ?: ""] = part.body
    }
    return params
}

suspend fun <T : Any> safeApiCall(apiCall: suspend () -> Response<T>): ResponseState<T> {
    return try {
        val apiResponse = apiCall.invoke()
        if (apiResponse.isSuccessful) {
            Log.e("testApp", "api call is success")
            apiResponse.body()?.let {
                ResponseState.Success(it)
            } ?: ResponseState.NullData()
        } else if (apiResponse.code() == 401) {
            ResponseState.Unauthorized()
        } else {
            Log.e("testApp", "api call is not  success")

            val error = apiResponse.errorBody()?.parseErrorBody<BaseResponse>()
            val errorMessage = error?.statusMessage ?: "Unknown error"
            ResponseState.Error(errorMessage)
        }
    } catch (e: IOException) {
        ResponseState.Error(e.message ?: "Network error")
    } catch (e: Exception) {
        ResponseState.Error(e.message ?: "Unknown error")
    }
}

suspend fun <T : Any, R : Any> safeApiCall(
    apiCall: suspend () -> Response<T>,
    mapper: (T) -> R
): ResponseState<R> {
    return try {
        val apiResponse = apiCall.invoke()
        if (apiResponse.isSuccessful) {
            apiResponse.body()?.let {
                ResponseState.Success(mapper(it))
            } ?: ResponseState.NullData()
        } else if (apiResponse.code() == 401) {
            ResponseState.Unauthorized()
        } else {
            val error = apiResponse.errorBody()?.parseErrorBody<BaseResponse>()
            val errorMessage = error?.statusMessage ?: "Unknown error"
            ResponseState.Error(errorMessage)
        }
    } catch (e: IOException) {
        ResponseState.Error(e.message ?: "Network error")
    } catch (e: Exception) {
        ResponseState.Error(e.message ?: "Unknown error")
    }
}

inline fun <reified T> ResponseBody?.parseErrorBody(): T? {
    return try {
        val errorResponseString = this?.string() ?: return null
        Gson().fromJson(errorResponseString, T::class.java)
    } catch (e: JsonParseException) {
        null
    }
}


