package com.example.cinedrivein.presentation.feature.home.state

import com.google.firebase.auth.FirebaseUser

data class HomeState(
    val user: FirebaseUser? = null,
    val isLogged: Boolean = true
)
