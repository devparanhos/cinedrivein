package com.example.cinedrivein.presentation.feature.register.state

data class RegisterState(
    var name: String = "",
    var email: String = "",
    var password: String = "",
    var confirmPassword: String = "",
)
