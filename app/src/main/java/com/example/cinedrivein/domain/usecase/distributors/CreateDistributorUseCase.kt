package com.example.cinedrivein.domain.usecase.distributors

import com.example.cinedrivein.domain.handler.RequestHandler
import com.example.cinedrivein.domain.model.Request
import com.example.cinedrivein.domain.model.distributor.Distributor
import com.example.cinedrivein.domain.repository.DistributorsRepository

class CreateDistributorUseCase(
    private val repository: DistributorsRepository
) {
    suspend fun createDistributor(distributor: Distributor, onHandler: RequestHandler){
        repository.createDistributor(distributor = distributor){ request ->
            when(request){
                is Request.Success -> onHandler.onSuccess(data = request.data)
                is Request.Error -> onHandler.onError(data = request.data, message = request.message)
            }
        }
    }
}