package com.example.cinedrivein.domain.usecase

import com.example.cinedrivein.domain.handler.RequestHandler
import com.example.cinedrivein.domain.model.Request
import com.example.cinedrivein.domain.repository.UserRepository

class RegisterUseCase(private val repository: UserRepository) {
    suspend fun register(
        name: String,
        email: String,
        password: String,
        ancineNumber: String,
        onHandler: RequestHandler
    ){
        repository.register(name = name, email = email, password = password, ancineNumber = ancineNumber){ request ->
            when(request){
                is Request.Success -> onHandler.onSuccess(data = request.data)
                is Request.Error -> onHandler.onError(data = request.data, message = request.message)
            }
        }
    }
}