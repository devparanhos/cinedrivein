package com.example.cinedrivein.domain.repository

import com.example.cinedrivein.domain.model.Request
import com.google.firebase.auth.FirebaseUser

interface LoginRepository {
    suspend fun login(email: String, password: String, onHandler: (Request) -> Unit)
    suspend fun checkUser(onHandler: (FirebaseUser?) -> Unit)
    suspend fun logout(onHandler: (FirebaseUser?) -> Unit)
}