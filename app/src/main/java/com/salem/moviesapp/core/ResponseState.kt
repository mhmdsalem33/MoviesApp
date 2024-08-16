package com.salem.moviesapp.core

sealed class ResponseState<T>(
    val data     : T?       = null ,
    val message  : String ? = null,
    val errors   : T?       = null,
    )
{
    class Success<T>(data : T)       : ResponseState<T>(data = data)
    class ErrorList<T>(errors: T)    : ResponseState<T>(errors = errors)
    class Error<T>(message: String)  : ResponseState<T>(message = message)
    class Loading<T>      : ResponseState<T>()
    class Idle<T>         : ResponseState<T>()
    class EmptyData<T>    : ResponseState<T>()
    class NullData<T>     : ResponseState<T>()
    class Unauthorized<T> : ResponseState<T>()
    class InternetConnectionError<T> : ResponseState<T>()
}
