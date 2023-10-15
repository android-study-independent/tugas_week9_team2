package com.vicryfahreza.msibmovieapp.data_models

data class Resource<T>(
    val isLoading: Boolean = false,
    val data: T? = null,
    val error: String? = null,
)
