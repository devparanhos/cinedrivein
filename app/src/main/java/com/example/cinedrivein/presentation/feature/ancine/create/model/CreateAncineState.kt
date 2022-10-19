package com.example.cinedrivein.presentation.feature.ancine.create.model

import com.example.cinedrivein.domain.model.AncineRequest

data class CreateAncineState(
    var canCheckSession: Boolean = false,
    var canSaveReport: Boolean = false,
    var sessionCount: Int = 0,
    var loading: Boolean = false,
    var ancineRequest: AncineRequest? = null,
    var ancineForm: CreateAncineFields = CreateAncineFields()
){
    companion object{
        val Empty = CreateAncineState()
    }
}

