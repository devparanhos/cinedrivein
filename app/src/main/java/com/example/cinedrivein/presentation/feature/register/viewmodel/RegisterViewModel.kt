package com.example.cinedrivein.presentation.feature.register.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinedrivein.common.utils.extensions.*
import com.example.cinedrivein.domain.handler.RequestHandler
import com.example.cinedrivein.domain.model.user.User
import com.example.cinedrivein.domain.usecase.user.CreateUserUseCase
import com.example.cinedrivein.domain.usecase.user.RegisterUseCase
import com.example.cinedrivein.presentation.feature.register.action.RegisterAction
import com.example.cinedrivein.presentation.feature.register.state.RegisterState
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase,
    private val createUserUseCase: CreateUserUseCase
):ViewModel() {
    private val _state = MutableStateFlow(RegisterState())
    val state: StateFlow<RegisterState> = _state.asStateFlow()

    fun submitAction(action: RegisterAction){
        when(action){
            is RegisterAction.UpdateEmail -> _state.value = _state.value.copy(
                email = action.email,
                emailInputError = null
            )
            is RegisterAction.UpdateName -> _state.value = _state.value.copy(
                name = action.name,
                nameInputError = null
            )
            is RegisterAction.UpdatePassword -> _state.value = _state.value.copy(
                password = action.password,
                passwordInputError = null
            )
            is RegisterAction.UpdateConfirmPassword -> _state.value = _state.value.copy(
                confirmPassword = action.confirmPassword,
                confirmPasswordInputError = null
            )
            is RegisterAction.UpdateAncineNumber -> _state.value = _state.value.copy(
                ancineNumber = action.ancineNumber,
                ancineNumberInputError = null
            )
            is RegisterAction.ValidateInputs -> validateInputs(
                name = _state.value.name,
                email = _state.value.email,
                password = _state.value.password,
                confirmPassword = _state.value.confirmPassword,
                ancineNumber = _state.value.ancineNumber
            )
        }
    }

    private fun validateInputs(name: String, email: String, password: String, confirmPassword: String, ancineNumber: String){
        _state.value = _state.value.copy(isRequesting = true, hasRequestingError = false)

        when{
            name.validateText(prefix = "O nome").isValid &&
            email.validateEmail().isValid &&
            password.validatePassword().isValid &&
            confirmPassword.validateConfirmPassword(password).isValid &&
            ancineNumber.validateAncineNumber().isValid -> register(
                name = name,
                email = email,
                password = password,
                ancineNumber = ancineNumber
            )

            else -> _state.value = _state.value.copy(
                isRequesting = false,
                nameInputError = name.validateText(prefix = "O nome").errorMessage,
                emailInputError = email.validateEmail().errorMessage,
                passwordInputError = password.validatePassword().errorMessage,
                confirmPasswordInputError = confirmPassword.validateConfirmPassword(password).errorMessage,
                ancineNumberInputError = ancineNumber.validateAncineNumber().errorMessage
            )
        }
    }

    private fun register(
        name: String,
        email: String,
        password: String,
        ancineNumber: String
    ){
        viewModelScope.launch(Dispatchers.IO) {
            registerUseCase.register(
                name = name,
                email = email,
                password = password,
                ancineNumber = ancineNumber,
                onHandler = object : RequestHandler{
                    override fun onSuccess(data: Any?) {
                        data as FirebaseUser
                        createUser(name = name, email = email, ancineNumber = ancineNumber, user = data)
                    }

                    override fun onError(data: Any?, message: String?) {
                        _state.value = _state.value.copy(
                            hasRequestingError = true,
                            isRequesting = false,
                            requestingError = message ?: "Algo inesperado ocorreu, tente novamente"
                        )
                    }
                }
            )
        }
    }

    private fun createUser(name: String, email: String, ancineNumber: String, user: FirebaseUser){
        viewModelScope.launch(Dispatchers.IO){
            createUserUseCase.createUser(
                User(
                    name = name,
                    email = email,
                    ancineNumber = ancineNumber.toInt(),
                    uid = user.uid
                ),
                onHandler = object : RequestHandler{
                    override fun onSuccess(data: Any?) {
                        _state.value = _state.value.copy(user = user)
                    }

                    override fun onError(data: Any?, message: String?) {
                        _state.value = _state.value.copy(
                            isRequesting = false,
                            hasRequestingError = true,
                            requestingError = message
                        )
                    }
                }
            )
        }
    }
}