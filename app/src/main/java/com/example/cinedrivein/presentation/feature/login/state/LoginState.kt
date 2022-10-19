package com.example.cinedrivein.presentation.feature.login.state

data class LoginState(
    var isLoading: Boolean = true,
    var email: String = "",
    var password: String = "",
    var isRequesting: Boolean = false,
    var passwordInputError: String? = null,
    var emailInputError: String? = null
)