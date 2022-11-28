package com.example.cinedrivein.data.repository

import com.example.cinedrivein.data.remote.service.firestore.FirestoreService
import com.example.cinedrivein.domain.model.Request
import com.example.cinedrivein.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val firestoreService: FirestoreService
): MovieRepository {
    override suspend fun getMovies(page: String, onHandler: (Request) -> Unit) {
        firestoreService.getMovies(page = page, onHandler = onHandler)
    }
}