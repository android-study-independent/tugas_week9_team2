package com.vicryfahreza.msibmovieapp.data_models

interface IMovie {
    val id: Int
    val title: String
    val posterPath: String?
    val backdropPath: String?
    val overview: String
    val releaseDate: String?
    val voteAverage: Double
}