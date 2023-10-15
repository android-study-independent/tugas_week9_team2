package com.vicryfahreza.msibmovieapp.extensions

import com.vicryfahreza.msibmovieapp.constants.IMAGE_BASE_URL
import com.vicryfahreza.msibmovieapp.constants.ImageSize
import com.vicryfahreza.msibmovieapp.data_models.Episode

fun Episode.getStillUrl(size: ImageSize = ImageSize.ORIGINAL): String {
    return "$IMAGE_BASE_URL${size.value}${this.stillPath}"
}
