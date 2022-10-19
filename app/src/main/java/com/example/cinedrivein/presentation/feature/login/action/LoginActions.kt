package com.example.cinedrivein.presentation.feature.login.action

sealed class LoginActions {
    class Login(val email: String, val password: String): LoginActions()
    class UpdateEmail(val email: String):LoginActions()
    class UpdatePassword(val password: String):LoginActions()
}
