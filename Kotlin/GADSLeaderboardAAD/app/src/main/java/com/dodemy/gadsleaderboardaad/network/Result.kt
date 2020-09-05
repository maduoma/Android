package com.dodemy.gadsleaderboardaad.network

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()
}


sealed class LoadingStatus() {
    class Loading(val message: String) : LoadingStatus()
    object Success : LoadingStatus()
    class Error(val message: String) : LoadingStatus()
}