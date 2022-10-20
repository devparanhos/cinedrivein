package com.example.cinedrivein.presentation.feature.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinedrivein.domain.usecase.LogoutUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val logoutUseCase: LogoutUseCase
): ViewModel() {
    fun logout(onHandler: (Boolean) -> Unit){
        viewModelScope.launch {
            logoutUseCase.logout(){
                onHandler(it != null)
            }
        }
    }
}