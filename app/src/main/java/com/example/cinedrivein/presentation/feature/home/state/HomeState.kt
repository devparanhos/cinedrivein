package com.example.cinedrivein.presentation.feature.home.state

import com.example.cinedrivein.domain.model.user.User

data class HomeState(
    val user: User? = null,
    val isLogged: Boolean = true,
    val isLoading: Boolean = true
)
