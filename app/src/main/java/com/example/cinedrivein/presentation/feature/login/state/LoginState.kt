package com.example.cinedrivein.presentation.feature.login.state

import com.google.firebase.auth.FirebaseUser

data class LoginState(
    val isSendingEmail: Boolean = false,
    val isLoading: Boolean = true,
    val email: String = "",
    val password: String = "",
    val isRequesting: Boolean = false,
    val passwordInputError: String? = null,
    val emailInputError: String? = null,
    val hasRequestError: Boolean = false,
    val messageError: String? = null,
    val user: FirebaseUser? = null,
    val exception: Exception? = null
)