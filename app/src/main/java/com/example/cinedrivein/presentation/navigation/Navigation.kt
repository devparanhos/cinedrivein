package com.example.cinedrivein.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cinedrivein.presentation.feature.login.view.BuildLoginScreen

@Composable
fun Navigation(navController:NavHostController = rememberNavController()){
    NavHost(navController = navController, startDestination = Screen.Login.route){
        composable(Screen.Login.route){
            BuildLoginScreen()
        }
    }
}