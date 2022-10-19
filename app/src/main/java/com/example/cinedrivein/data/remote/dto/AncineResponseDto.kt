package com.example.cinedrivein.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AncineResponseDto(
    @SerializedName("diaCinematografico")
    val date: String,
    @SerializedName("mensagens")
    val messages: List<Any>,
    @SerializedName("numeroProtocolo")
    val protocol: String,
    @SerializedName("registroANCINEExibidor")
    val ancineCinemaId: String,
    @SerializedName("registroANCINESala")
    val ancineRoomId: String,
    @SerializedName("statusProtocolo")
    val status: String
)