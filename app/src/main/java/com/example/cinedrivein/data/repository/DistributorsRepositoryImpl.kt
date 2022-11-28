package com.example.cinedrivein.data.repository

import com.example.cinedrivein.data.remote.service.firestore.FirestoreService
import com.example.cinedrivein.domain.model.Request
import com.example.cinedrivein.domain.model.distributor.Distributor
import com.example.cinedrivein.domain.repository.DistributorsRepository

class DistributorsRepositoryImpl(
    private val firestoreService: FirestoreService
): DistributorsRepository {
    override suspend fun getDistributors(onHandler: (Request) -> Unit) {
        firestoreService.getDistributors(onHandler = { onHandler(it) })
    }

    override suspend fun deleteDistributor(reference: String, onHandler: (Request) -> Unit) {
        firestoreService.deleteDistributors(reference = reference, onHandler = { onHandler(it) })
    }

    override suspend fun createDistributor(distributor: Distributor, onHandler: (Request) -> Unit) {
        firestoreService.createDistributors(distributor = distributor, onHandler = { onHandler(it)})
    }

    override suspend fun updateDistributor(distributor: Distributor, onHandler: (Request) -> Unit) {
        firestoreService.updateDistributors(distributor = distributor, onHandler = { onHandler(it)} )
    }
}