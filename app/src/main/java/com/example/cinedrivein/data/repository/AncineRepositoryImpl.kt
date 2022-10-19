package com.example.cinedrivein.data.repository

import com.example.cinedrivein.data.remote.dto.AncineRequestDto
import com.example.cinedrivein.data.remote.dto.AncineResponseDto
import com.example.cinedrivein.data.remote.service.ancine.AncineApi
import com.example.cinedrivein.domain.repository.AncineRepository
import retrofit2.Response

class AncineRepositoryImpl(
    private val api: AncineApi
): AncineRepository {
    override suspend fun sendReport(ancineRequestDto: AncineRequestDto): Response<AncineResponseDto> {
        return api.sendReport(ancineRequestDto)
    }
}