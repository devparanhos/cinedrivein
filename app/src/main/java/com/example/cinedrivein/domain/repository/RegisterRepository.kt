package com.example.cinedrivein.domain.repository

import com.example.cinedrivein.domain.model.Request

interface RegisterRepository {
    suspend fun register(
        name: String,
        email: String,
        password: String,
        ancineNumber: String,
        onHandler: (Request) -> Unit
    )
}