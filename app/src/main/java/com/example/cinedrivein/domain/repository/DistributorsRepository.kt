package com.example.cinedrivein.domain.repository

import com.example.cinedrivein.domain.model.Request

interface DistributorsRepository {
    suspend fun getDistributors(onHandler: (Request) -> Unit)
    suspend fun deleteDistributor(id: String, onHandler: (Request) -> Unit)
}