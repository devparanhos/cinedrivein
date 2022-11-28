package com.example.cinedrivein.presentation.feature.movie.list.action

sealed class MoviesAction{
    object GetMovies: MoviesAction()
    data class UpdateSearch(val search: String): MoviesAction()
}
