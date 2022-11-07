package com.example.cinedrivein.data.repository

import com.example.cinedrivein.data.remote.service.firestore.FirestoreService
import com.example.cinedrivein.domain.model.Request
import com.example.cinedrivein.domain.repository.DistributorsRepository

class DistributosRepositoryImpl(
    private val firestoreService: FirestoreService
): DistributorsRepository {
    override suspend fun getDistributors(onHandler: (Request) -> Unit) {
        firestoreService.getDistributors {
            onHandler(it)
        }
    }

    override suspend fun deleteDistributor(id: String, onHandler: (Request) -> Unit) {
        firestoreService.deleteDistributors(
            id = id,
            onHandler = { onHandler(it) }
        )
    }
}