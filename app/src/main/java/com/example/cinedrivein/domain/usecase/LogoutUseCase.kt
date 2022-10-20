package com.example.cinedrivein.domain.usecase

import com.example.cinedrivein.domain.repository.LoginRepository
import com.google.firebase.auth.FirebaseUser

class LogoutUseCase(private val repository: LoginRepository) {
    suspend fun logout(onHandler: (FirebaseUser?) -> Unit){
        repository.logout(onHandler = onHandler)
    }
}