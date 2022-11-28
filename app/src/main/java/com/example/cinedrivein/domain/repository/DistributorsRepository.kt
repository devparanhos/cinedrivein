package com.example.cinedrivein.domain.repository

import com.example.cinedrivein.domain.model.Request
import com.example.cinedrivein.domain.model.distributor.Distributor

interface DistributorsRepository {
    suspend fun getDistributors(onHandler: (Request) -> Unit)
    suspend fun deleteDistributor(reference: String, onHandler: (Request) -> Unit)
    suspend fun createDistributor(distributor: Distributor, onHandler: (Request) -> Unit)
    suspend fun updateDistributor(distributor: Distributor, onHandler: (Request) -> Unit)
}