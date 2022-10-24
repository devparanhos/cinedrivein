package com.example.cinedrivein.presentation.feature.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinedrivein.common.utils.extensions.validateEmail
import com.example.cinedrivein.common.utils.extensions.validatePassword
import com.example.cinedrivein.domain.handler.RequestHandler
import com.example.cinedrivein.domain.usecase.CheckUserUseCase
import com.example.cinedrivein.domain.usecase.LoginUseCase
import com.example.cinedrivein.presentation.feature.login.action.LoginActions
import com.example.cinedrivein.presentation.feature.login.state.LoginState
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val checkUserUseCase: CheckUserUseCase
): ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    init {
        isUserLogged()
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
        _state.value = _state.value.copy(isRequesting = true, hasRequestError = false)
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
        viewModelScope.launch(Dispatchers.IO) {
            loginUseCase.login(
                email = email,
                password = password,
                handler = object : RequestHandler{
                    override fun onSuccess(data: Any?) {
                        _state.value = _state.value.copy(user = data as FirebaseUser)
                    }

                    override fun onError(data: Any?, message: String?) {
                        _state.value = _state.value.copy(
                            isRequesting = false,
                            hasRequestError = true,
                            messageError = message ?: "Algo inesperado ocorreu. Tente novamente",
                            exception = data as Exception
                        )
                    }
                }
            )
        }
    }

    private fun isUserLogged(){
        viewModelScope.launch(Dispatchers.IO){
            delay(1000)
            checkUserUseCase.checkUser(){ user ->
                when(user != null){
                    true -> _state.value = _state.value.copy(user = user, isLoading = false)
                    false -> _state.value = _state.value.copy(isLoading = false)
                }
            }
        }
    }
}