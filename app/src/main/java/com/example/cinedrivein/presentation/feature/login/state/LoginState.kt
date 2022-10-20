package com.example.cinedrivein.presentation.feature.login.state

import com.google.firebase.auth.FirebaseUser

data class LoginState(
    var isLoading: Boolean = true,
    var email: String = "",
    var password: String = "",
    var isRequesting: Boolean = false,
    var passwordInputError: String? = null,
    var emailInputError: String? = null,
    var hasRequestError: Boolean = false,
    var messageError: String? = null,
    var user: FirebaseUser? = null,
    var exception: Exception? = null
)