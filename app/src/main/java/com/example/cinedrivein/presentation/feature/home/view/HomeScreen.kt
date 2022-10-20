package com.example.cinedrivein.presentation.feature.home.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.cinedrivein.R
import com.example.cinedrivein.presentation.feature.home.viewmodel.HomeViewModel
import com.example.cinedrivein.presentation.navigation.Screen
import org.koin.androidx.compose.getViewModel

@Composable
fun BuildHomeScreen(
    viewModel: HomeViewModel = getViewModel(),
    onNavigation: (String) -> Unit
){
    Text(text = "teste")
    Image(painter = painterResource(id = R.drawable.ic_exit), contentDescription = null, modifier = Modifier.clickable {
        viewModel.logout(){ isLogged ->
            when(isLogged){
                true -> {

                }
                false -> onNavigation(Screen.Login.route)
            }
        }
    })
}