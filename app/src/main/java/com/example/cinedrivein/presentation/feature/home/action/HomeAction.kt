package com.example.cinedrivein.presentation.feature.home.action

sealed class HomeAction{
    data class GetUser(val userUid: String?): HomeAction()
}
