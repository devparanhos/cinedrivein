package com.example.cinedrivein.presentation.feature.home.action

sealed class HomeAction{
    object Logout: HomeAction()
    data class GetUser(val userUid: String?): HomeAction()
}
