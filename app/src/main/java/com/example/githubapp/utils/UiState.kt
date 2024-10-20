package com.example.githubapp.utils

sealed interface UiState<out T> {

    data class Success<T>(val data: T): UiState<T>

    data class Error(val e: String): UiState<Nothing>

    object Loading: UiState<Nothing>
}