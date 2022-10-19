package com.example.cinedrivein.domain.repository

import com.example.cinedrivein.data.remote.dto.AncineRequestDto
import com.example.cinedrivein.data.remote.dto.AncineResponseDto
import retrofit2.Response

interface AncineRepository {
    suspend fun sendReport(ancineRequestDto: AncineRequestDto): Response<AncineResponseDto>
}