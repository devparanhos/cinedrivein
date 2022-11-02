package com.example.cinedrivein.domain.repository

import com.example.cinedrivein.domain.model.Request
import com.example.cinedrivein.domain.model.user.User

interface RegisterRepository {
    suspend fun register(
        name: String,
        email: String,
        password: String,
        ancineNumber: String,
        onHandler: (Request) -> Unit
    )

    suspend fun createUser(
        user: User,
        onHandler: (Request) -> Unit
    )
}