package com.example.cinedrivein.presentation.feature.register.state

data class RegisterState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val ancineNumber: String = ""
)
