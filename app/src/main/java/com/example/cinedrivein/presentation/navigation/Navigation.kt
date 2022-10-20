package com.example.cinedrivein.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cinedrivein.presentation.feature.home.view.BuildHomeScreen
import com.example.cinedrivein.presentation.feature.login.view.BuildLoginScreen
import com.example.cinedrivein.presentation.feature.register.view.BuildRegisterScreen

@Composable
fun Navigation(navController:NavHostController = rememberNavController()){
    NavHost(navController = navController, startDestination = Screen.Login.route){
        composable(Screen.Login.route){
            BuildLoginScreen(){ route ->
                when(route){
                    Screen.Home.route -> navController.navigate(route){
                        popUpTo(0)
                    }
                    else -> navController.navigate(route)
                }
            }
        }
        composable(Screen.Home.route){
            BuildHomeScreen(){ route ->
                when(route){
                    Screen.Login.route ->  navController.navigate(route){
                        popUpTo(0)
                    }
                }
            }
        }
        composable(Screen.Register.route){
            BuildRegisterScreen(){ route ->
                navController.popBackStack(route = route, inclusive = false)
            }
        }
    }
}