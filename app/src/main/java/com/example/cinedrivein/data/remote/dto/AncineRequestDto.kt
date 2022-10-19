package com.example.cinedrivein.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AncineRequestDto(
    @SerializedName("diaCinematografico")
    val date: String,
    @SerializedName("houveSessoes")
    val hasSession: String,
    @SerializedName("registroANCINEExibidor")
    val ancineCinemaId: String,
    @SerializedName("registroANCINESala")
    val ancineRoomId: String,
    @SerializedName("retificador")
    val rectifying: String,
    @SerializedName("sessoes")
    val sessions: List<Session>
)