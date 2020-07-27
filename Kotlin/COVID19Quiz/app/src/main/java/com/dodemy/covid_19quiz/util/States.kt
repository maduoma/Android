package com.project.segunfrancis.qvid19.util

/**
 * Created by SegunFrancis
 */
sealed class States<T> {
    data class Success<T>(var message: String, var data: T): States<T>()
    data class Error<T>(var message: String, var exception: Throwable): States<T>()
    data class Loading<T>(var message: String): States<T>()
}