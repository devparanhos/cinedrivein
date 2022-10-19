package com.example.cinedrivein.presentation.feature.ancine.create.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinedrivein.domain.model.AncineRequest
import com.example.cinedrivein.domain.model.toModel
import com.example.cinedrivein.domain.usecase.SendAncineReportUseCase
import com.example.cinedrivein.presentation.feature.ancine.create.model.CreateAncineEvents
import com.example.cinedrivein.presentation.feature.ancine.create.model.CreateAncineState
import kotlinx.coroutines.launch

class CreateAncineReportViewModel(
    private val sendAncineReportUseCase: SendAncineReportUseCase
): ViewModel() {

    private val _state = mutableStateOf(CreateAncineState.Empty)
    val state: State<CreateAncineState> = _state

    fun viewEvent(event: CreateAncineEvents){
        when(event){
            is CreateAncineEvents.SendReportEvent -> sendAncine(event.param)
            is CreateAncineEvents.IncrementSession -> _state.value = _state.value.copy(sessionCount = event.quantity)
        }
    }

    private fun sendAncine(ancineRequest: AncineRequest){
        viewModelScope.launch {
            sendAncineReportUseCase.sendReport(
                ancineRequest = ancineRequest.toModel(),
                onRequest = {

                }
            )
        }
    }
}