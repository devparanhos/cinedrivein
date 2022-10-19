package com.example.cinedrivein.domain.usecase

import com.example.cinedrivein.data.remote.dto.AncineRequestDto
import com.example.cinedrivein.domain.repository.AncineRepository

class SendAncineReportUseCase(
    private val repository: AncineRepository
) {
    suspend fun sendReport(ancineRequest: AncineRequestDto, onRequest: () -> Unit){
        when(repository.sendReport(ancineRequest).isSuccessful){
            true -> onRequest.invoke()
            false -> onRequest.invoke()
        }
    }
}