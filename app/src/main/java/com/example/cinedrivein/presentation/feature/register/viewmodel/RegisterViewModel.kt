package com.example.cinedrivein.presentation.feature.register.viewmodel

import androidx.lifecycle.ViewModel
import com.example.cinedrivein.presentation.feature.register.action.RegisterAction
import com.example.cinedrivein.presentation.feature.register.state.RegisterState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RegisterViewModel:ViewModel() {
    private val _state = MutableStateFlow(RegisterState())
    val state: StateFlow<RegisterState> = _state.asStateFlow()

    fun submitAction(action: RegisterAction){
        when(action){
            is RegisterAction.UpdateEmail -> _state.value = _state.value.copy(email = action.email)
        }
    }
}