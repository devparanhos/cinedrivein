package com.example.cinedrivein.domain.usecase

import com.example.cinedrivein.domain.repository.LoginRepository
import com.google.firebase.auth.FirebaseUser

class CheckUserUseCase(
    private val repository: LoginRepository
) {
    suspend fun checkUser(onHandler: (FirebaseUser?) -> Unit){
        repository.checkUser(onHandler)
    }
}