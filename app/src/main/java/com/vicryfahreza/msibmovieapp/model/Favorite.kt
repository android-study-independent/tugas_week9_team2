package com.vicryfahreza.msibmovieapp.model

import java.util.Date

data class Favorite(
    val id: String? = "",
    val url: String?,
    val title: String?,
    val description: String?,
    val createdAt: Date
)
