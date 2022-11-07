package com.example.cinedrivein.domain.usecase.user

import com.example.cinedrivein.domain.handler.RequestHandler
import com.example.cinedrivein.domain.model.Request
import com.example.cinedrivein.domain.repository.UserRepository

class GetUserUseCase(private val repository: UserRepository) {
    suspend fun getUser(userUid: String, onHandler: RequestHandler){
        repository.getUser(userUid = userUid){ request ->
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