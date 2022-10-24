package com.example.cinedrivein.presentation.feature.register.state

import com.google.firebase.auth.FirebaseUser

data class RegisterState(
    val user: FirebaseUser? = null,
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val ancineNumber: String = "",
    val isRequesting: Boolean = false,
    val nameInputError: String? = null,
    val emailInputError: String? = null,
    val passwordInputError: String? = null,
    val confirmPasswordInputError: String? = null,
    val ancineNumberInputError: String? = null,
    val hasRequestingError: Boolean = false,
    val requestingError: String? = null
)
