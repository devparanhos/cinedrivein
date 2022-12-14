package com.example.cinedrivein.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cinedrivein.common.utils.extensions.fromJson
import com.example.cinedrivein.domain.model.distributor.Distributor
import com.example.cinedrivein.domain.model.user.User
import com.example.cinedrivein.presentation.feature.distributors.create.view.BuildCreateDistributor
import com.example.cinedrivein.presentation.feature.distributors.list.view.BuildDistributorsScreen
import com.example.cinedrivein.presentation.feature.home.view.BuildHomeScreen
import com.example.cinedrivein.presentation.feature.login.view.BuildLoginScreen
import com.example.cinedrivein.presentation.feature.menu.view.BuildMenuScreen
import com.example.cinedrivein.presentation.feature.movie.list.view.BuildMoviesScreen
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
                when(route.substringBefore(delimiter = "/")){
                    Screen.Login.route ->  navController.navigate(route){
                        popUpTo(0)
                    }

                    else -> navController.navigate(route)
                }
            }
        }

        composable(Screen.Register.route){
            BuildRegisterScreen(){ route ->
                when(route.substringBefore(delimiter = "/")){
                    Screen.Home.route -> navController.navigate(route){
                        popUpTo(0)
                    }

                    Screen.Login.route -> navController.popBackStack()

                    else -> navController.navigate(route)
                }
            }
        }

        composable(Screen.Menu.route + "/{user}", arguments = listOf(navArgument("user"){ type = NavType.StringType })){ it ->
            val user = it.arguments?.getString("user")
            user?.let { user ->
                BuildMenuScreen(user = user.fromJson(User::class.java)){ route ->
                    when(route){
                        Screen.Login.route -> navController.navigate(route){
                            popUpTo(0)
                        }

                        Screen.Distributors.route -> navController.navigate(route)

                        Screen.Movies.route -> navController.navigate(route)

                        else -> navController.popBackStack()
                    }
                }
            }
        }

        composable(Screen.Distributors.route){
            BuildDistributorsScreen { route ->
                when(route.substringBefore(delimiter = "?")){
                    Screen.CreateDistributor.route -> {
                        navController.navigate(route)
                    }

                    else -> navController.popBackStack()
                }
            }
        }

        composable(Screen.Movies.route){
            BuildMoviesScreen() { route ->
                when(route){
                    else -> navController.popBackStack()
                }
            }
        }

        composable(
            route = Screen.CreateDistributor.route + "?distributor={distributor}",
            arguments = listOf(
                navArgument("distributor"){
                    type = NavType.StringType
                    nullable = true
                }
            )
        ){
            val distributor = it.arguments?.getString("distributor")
            BuildCreateDistributor(distributor = distributor?.fromJson(Distributor::class.java)){ route ->
                when(route){
                    else -> {
                        navController.popBackStack()
                    }
                }
            }
        }
    }
}