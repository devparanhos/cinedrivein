package com.example.cinedrivein.presentation.feature.menu.state

import com.example.cinedrivein.domain.model.user.User

data class MenuState(
    val user: User? = null,
    val isLogged: Boolean = true
)