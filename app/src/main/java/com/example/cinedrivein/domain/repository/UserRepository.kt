package com.example.cinedrivein.domain.repository

import com.example.cinedrivein.domain.model.Request
import com.example.cinedrivein.domain.model.user.User
import com.google.firebase.auth.FirebaseUser

interface UserRepository {
    suspend fun login(email: String, password: String, onHandler: (Request) -> Unit)
    suspend fun checkUser(onHandler: (FirebaseUser?) -> Unit)
    suspend fun logout(onHandler: (FirebaseUser?) -> Unit)
    suspend fun getUser(userUid: String, onHandler: (Request) -> Unit)
    suspend fun recoverPassword(email: String, onHandler: (Request) -> Unit)
    suspend fun register(name: String, email: String, password: String, ancineNumber: String, onHandler: (Request) -> Unit)
    suspend fun createUser(user: User, onHandler: (Request) -> Unit)
}