package com.example.cinedrivein.domain.usecase.login

import com.example.cinedrivein.domain.handler.RequestHandler
import com.example.cinedrivein.domain.model.Request
import com.example.cinedrivein.domain.repository.UserRepository

class RecoverPasswordUseCase(private val repository: UserRepository) {
    suspend fun recoverPassword(email: String, onHandler: RequestHandler){
        repository.recoverPassword(email = email){ request ->
            when(request){
                is Request.Success -> onHandler.onSuccess(data = request.data)
                is Request.Error -> onHandler.onError(data = request.data, message = request.message)
            }
        }
    }
}