package com.example.cinedrivein.presentation.feature.movie.list.state

import com.example.cinedrivein.domain.model.movie.Movie

data class MoviesState(
    val isLoading: Boolean = true,
    val movies: List<Movie> = listOf(),
    val search: String = "",
    val page: Int = 1
)
