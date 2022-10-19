package com.example.cinedrivein.presentation.feature.ancine.create.model

import com.example.cinedrivein.domain.model.AncineRequest

sealed class CreateAncineEvents {
    data class SendReportEvent(val param: AncineRequest): CreateAncineEvents()
    data class IncrementSession(val quantity: Int): CreateAncineEvents()
}