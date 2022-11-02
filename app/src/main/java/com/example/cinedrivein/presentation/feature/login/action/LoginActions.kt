package com.example.cinedrivein.presentation.feature.login.action

sealed class LoginActions {
    data class Login(val email: String, val password: String): LoginActions()
    data class UpdateEmail(val email: String):LoginActions()
    data class UpdatePassword(val password: String):LoginActions()
    data class RecoverPassword(val email: String):LoginActions()
}
