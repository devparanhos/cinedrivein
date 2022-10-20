package com.example.cinedrivein.presentation.feature.register.action

sealed class RegisterAction{
    data class UpdateEmail(val email: String):RegisterAction()
}
