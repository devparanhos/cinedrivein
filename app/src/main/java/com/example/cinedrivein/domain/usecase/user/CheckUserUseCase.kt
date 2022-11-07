package com.example.cinedrivein.domain.usecase.user

import com.example.cinedrivein.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseUser

class CheckUserUseCase(
    private val repository: UserRepository
) {
    suspend fun checkUser(onHandler: (FirebaseUser?) -> Unit){
        repository.checkUser(onHandler)
    }
}