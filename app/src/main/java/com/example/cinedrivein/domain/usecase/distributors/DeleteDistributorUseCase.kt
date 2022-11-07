package com.example.cinedrivein.domain.usecase.distributors

import com.example.cinedrivein.domain.handler.RequestHandler
import com.example.cinedrivein.domain.model.Request
import com.example.cinedrivein.domain.repository.DistributorsRepository

class DeleteDistributorUseCase(
    private val repository: DistributorsRepository
) {
    suspend fun deleteDistributor(id: String, onHandler: RequestHandler){
        repository.deleteDistributor(id = id){ request ->
            when(request){
                is Request.Success -> onHandler.onSuccess(data = request.data)
                is Request.Error -> onHandler.onError(data = request.data, message = request.message)
            }
        }
    }
}