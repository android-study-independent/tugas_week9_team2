package com.vicryfahreza.msibmovieapp.extensions

import com.vicryfahreza.msibmovieapp.data_models.Media

fun Media.getId(): Int {
    return when (this) {
        is Media.Movie -> id
        else -> -1
    }
}