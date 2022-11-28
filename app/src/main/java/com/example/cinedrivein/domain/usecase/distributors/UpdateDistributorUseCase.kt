package com.example.cinedrivein.domain.usecase.distributors

import com.example.cinedrivein.domain.handler.RequestHandler
import com.example.cinedrivein.domain.model.Request
import com.example.cinedrivein.domain.model.distributor.Distributor
import com.example.cinedrivein.domain.repository.DistributorsRepository

class UpdateDistributorUseCase(val repository: DistributorsRepository) {
    suspend fun updateDistributor(distributor: Distributor, onHandler: RequestHandler){
        repository.updateDistributor(distributor = distributor){
            when(it){
                is Request.Success -> onHandler.onSuccess(data = it.data)
                is Request.Error -> onHandler.onError(data = it.data, message = it.message)
            }
        }
    }
}