package com.example.cinedrivein.domain.usecase

import com.example.cinedrivein.domain.handler.RequestHandler
import com.example.cinedrivein.domain.model.Request
import com.example.cinedrivein.domain.model.user.User
import com.example.cinedrivein.domain.repository.RegisterRepository

class CreateUserUseCase(
    private val repository: RegisterRepository
) {
    suspend fun createUser(user: User, onHandler: RequestHandler){
        repository.createUser(user = user){ request ->
            when(request){
                is Request.Success -> {
                    onHandler.onSuccess(data = request.data)
                }

                is Request.Error -> {
                    onHandler.onError(data = request.data, message = request.message)
                }
            }
        }
    }
}