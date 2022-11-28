package com.example.cinedrivein.domain.repository

import com.example.cinedrivein.domain.model.Request

interface MovieRepository {
    suspend fun getMovies(page: String, onHandler: (Request) -> Unit)
}