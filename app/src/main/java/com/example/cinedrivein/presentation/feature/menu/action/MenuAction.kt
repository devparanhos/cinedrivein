package com.example.cinedrivein.presentation.feature.menu.action

import com.example.cinedrivein.domain.model.user.User

sealed class MenuAction{
    object Logout: MenuAction()

    data class UpdateUser(val user: User): MenuAction()
}
