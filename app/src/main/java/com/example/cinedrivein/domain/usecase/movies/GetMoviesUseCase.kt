package com.example.cinedrivein.domain.usecase.movies

import com.example.cinedrivein.domain.handler.RequestHandler
import com.example.cinedrivein.domain.model.Request
import com.example.cinedrivein.domain.repository.MovieRepository

class GetMoviesUseCase(
    private val repository: MovieRepository
) {
    suspend fun getMovies(page: String, onHandler: RequestHandler){
        repository.getMovies(
            page = page,
            onHandler = { request ->
                when(request){
                    is Request.Success -> onHandler.onSuccess(data = request.data)
                    is Request.Error -> onHandler.onError(data = request.data, message = request.message)
                }
            }
        )
    }
}