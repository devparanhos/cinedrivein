package com.example.cinedrivein.presentation.feature.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinedrivein.common.utils.string.validateEmail
import com.example.cinedrivein.common.utils.string.validatePassword
import com.example.cinedrivein.domain.usecase.LoginUseCase
import com.example.cinedrivein.presentation.feature.login.action.LoginActions
import com.example.cinedrivein.presentation.feature.login.state.LoginState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
): ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            delay(5000)
            _state.value = _state.value.copy(isLoading = false)
        }
    }

    fun submitAction(actions: LoginActions){
        when(actions){
            is LoginActions.UpdateEmail -> _state.value = _state.value.copy(
                email = actions.email,
                emailInputError = null
            )
            is LoginActions.UpdatePassword -> _state.value = _state.value.copy(
                password = actions.password,
                passwordInputError = null
            )
            is LoginActions.Login -> validateInputs(email = actions.email, password = actions.password)
        }
    }

    private fun validateInputs(email: String, password: String){
        _state.value = _state.value.copy(isRequesting = true)
        when {
            email.validateEmail().isValid && password.validatePassword().isValid -> login(
                email = email,
                password = password
            )
            else -> _state.value = _state.value.copy(
                isRequesting = false,
                passwordInputError = password.validatePassword().errorMessage,
                emailInputError = email.validateEmail().errorMessage
            )
        }
    }

    private fun login(email: String, password: String){
        viewModelScope.launch {
            loginUseCase.tryLogin()
        }
    }
}