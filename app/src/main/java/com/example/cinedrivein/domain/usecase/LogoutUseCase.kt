package com.example.cinedrivein.domain.usecase

import com.example.cinedrivein.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseUser

class LogoutUseCase(private val repository: UserRepository) {
    suspend fun logout(onHandler: (FirebaseUser?) -> Unit){
        repository.logout(onHandler = onHandler)
    }
}