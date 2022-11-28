package com.example.cinedrivein.presentation.navigation

sealed class Screen(val route: String){
    object Login:Screen(route = "login")
    object Register:Screen(route = "register")
    object Home:Screen(route = "home")
    object Menu:Screen(route = "menu")
    object Distributors:Screen(route = "distributors")
    object CreateDistributor:Screen(route = "create_distributor")
    object Movies:Screen(route = "movies")
    object Return:Screen(route = "return")

}