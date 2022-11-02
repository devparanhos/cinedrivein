package com.example.cinedrivein.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cinedrivein.presentation.feature.home.view.BuildHomeScreen
import com.example.cinedrivein.presentation.feature.login.view.BuildLoginScreen
import com.example.cinedrivein.presentation.feature.register.view.BuildRegisterScreen

@Composable
fun Navigation(navController:NavHostController = rememberNavController()){
    NavHost(navController = navController, startDestination = Screen.Login.route){
        composable(Screen.Login.route){
            BuildLoginScreen(){ route ->
                when(route.substringBefore(delimiter = "/")){
                    Screen.Home.route -> navController.navigate(route){
                        popUpTo(0)
                    }
                    else -> navController.navigate(route)
                }
            }
        }
        composable(Screen.Home.route + "/{userUid}", arguments = listOf(navArgument("userUid") { type = NavType.StringType})){
            BuildHomeScreen(userUid = it.arguments?.getString("userUid")){ route ->
                when(route){
                    Screen.Login.route ->  navController.navigate(route){
                        popUpTo(0)
                    }
                }
            }
        }
        composable(Screen.Register.route){
            BuildRegisterScreen(){ route ->
                when(route.substringBefore(delimiter = "/")){
                    Screen.Home.route -> navController.navigate(route){
                        popUpTo(0)
                    }

                    else -> navController.navigate(route)
                }
            }
        }
    }
}