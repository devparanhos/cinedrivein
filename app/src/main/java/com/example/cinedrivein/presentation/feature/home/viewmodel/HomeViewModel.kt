package com.example.cinedrivein.presentation.feature.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinedrivein.domain.usecase.LogoutUseCase
import com.example.cinedrivein.presentation.feature.home.action.HomeAction
import com.example.cinedrivein.presentation.feature.home.state.HomeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val logoutUseCase: LogoutUseCase
): ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    fun submitAction(action: HomeAction){
        when(action){
            is HomeAction.Logout -> logout()
        }
    }

    private fun logout(){
        viewModelScope.launch {
            logoutUseCase.logout(){
                when(it){
                    null -> _state.value = _state.value.copy(isLogged = false)
                    else -> {

                    }
                }
            }
        }
    }
}