package com.example.cinedrivein.data.remote.service.ancine

import com.example.cinedrivein.data.remote.dto.AncineRequestDto
import com.example.cinedrivein.data.remote.dto.AncineResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AncineApi {
    @GET("v1.0/bilheterias")
    suspend fun sendReport(@Body ancineRequestDto: AncineRequestDto) :  Response<AncineResponseDto>
}