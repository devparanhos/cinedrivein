package com.example.cinedrivein.data.repository

import com.example.cinedrivein.domain.model.Request
import com.example.cinedrivein.domain.repository.RegisterRepository
import com.google.firebase.auth.FirebaseAuth

class RegisterRepositoryImpl(private val auth: FirebaseAuth): RegisterRepository {
    override suspend fun register(
        name: String,
        email: String,
        password: String,
        ancineNumber: String,
        onHandler: (Request) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            when{
                task.isSuccessful -> onHandler(Request.Success(data = task.result.user))
                else -> onHandler(Request.Error(data = task.exception, message = task.exception?.message))
            }
        }
    }
}