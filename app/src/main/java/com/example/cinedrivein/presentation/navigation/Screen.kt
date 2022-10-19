package com.example.cinedrivein.presentation.navigation

sealed class Screen(val route: String){
    object Login:Screen(route = "login")
}