package com.example.cinedrivein.domain.usecase.login

import com.example.cinedrivein.domain.repository.UserRepository
import com.example.cinedrivein.domain.handler.RequestHandler
import com.example.cinedrivein.domain.model.Request

class LoginUseCase(private val repository: UserRepository) {
    suspend fun login(email: String, password: String, handler: RequestHandler){
        repository.login(email = email, password = password){ request ->
           when(request){
               is Request.Success -> handler.onSuccess(data = request.data)
               is Request.Error -> handler.onError(data = request.data, message = request.message)
           }
        }
    }
}