package com.example.cinedrivein.domain.model

import com.example.cinedrivein.data.remote.dto.AncineRequestDto
import com.example.cinedrivein.data.remote.dto.Session

data class AncineRequest(
    val date: String,
    val hasSession: String,
    val ancineCinemaId: String,
    val ancineRoomId: String,
    val rectifying: String,
    val sessions: List<Session>
)

fun AncineRequest.toModel() : AncineRequestDto{
    return AncineRequestDto(
        date = this.date,
        hasSession = this.hasSession,
        ancineCinemaId = this.ancineCinemaId,
        ancineRoomId = this.ancineRoomId,
        rectifying = this.rectifying,
        sessions = this.sessions
    )
}
