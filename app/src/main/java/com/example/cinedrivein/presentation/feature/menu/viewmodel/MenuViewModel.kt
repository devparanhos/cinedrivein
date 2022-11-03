package com.example.cinedrivein.presentation.feature.menu.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinedrivein.domain.usecase.LogoutUseCase
import com.example.cinedrivein.presentation.feature.menu.action.MenuAction
import com.example.cinedrivein.presentation.feature.menu.state.MenuState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MenuViewModel(
    private val logoutUseCase: LogoutUseCase
): ViewModel() {

    private val _state =  MutableStateFlow(MenuState())
    val state: StateFlow<MenuState> = _state.asStateFlow()

    fun submitAction(action: MenuAction){
        when(action){
            is MenuAction.UpdateUser -> _state.value = _state.value.copy(user = action.user)
            is MenuAction.Logout -> logout()
        }
    }

    private fun logout(){
        viewModelScope.launch {
            logoutUseCase.logout {
                if (it == null) _state.value = _state.value.copy(user = null, isLogged = false)
            }
        }
    }
}