package com.example.cinedrivein.domain.usecase.distributors

import com.example.cinedrivein.domain.handler.RequestHandler
import com.example.cinedrivein.domain.model.Request
import com.example.cinedrivein.domain.model.distributor.Distributor
import com.example.cinedrivein.domain.repository.DistributorsRepository

class GetDistributorsUseCase(
    private val repository: DistributorsRepository
) {
    suspend fun getDistributors(onHandler: RequestHandler){
        repository.getDistributors { request ->  
            when(request){
                is Request.Success -> onHandler.onSuccess(data = request.data as List<Distributor>)
                is Request.Error -> onHandler.onError(data = null, message = null)
            }
        }
    }
}