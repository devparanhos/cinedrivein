package com.example.cinedrivein.presentation.feature.home.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cinedrivein.R
import com.example.cinedrivein.presentation.components.spacer.HeightSpacer
import com.example.cinedrivein.presentation.components.text.MainTitle
import com.example.cinedrivein.presentation.components.text.Subtitle
import com.example.cinedrivein.presentation.feature.home.action.HomeAction
import com.example.cinedrivein.presentation.feature.home.state.HomeState
import com.example.cinedrivein.presentation.feature.home.viewmodel.HomeViewModel
import com.example.cinedrivein.presentation.navigation.Screen
import com.example.cinedrivein.presentation.theme.Primary
import com.example.cinedrivein.presentation.theme.WhiteGray
import org.koin.androidx.compose.getViewModel

@Composable
fun BuildHomeScreen(
    viewModel: HomeViewModel = getViewModel(),
    onNavigation: (String) -> Unit
){
    val state by viewModel.state.collectAsState()

    when{
        state.isLogged -> HomeScreenLayout(
            state = state,
            onAction = {
                viewModel.submitAction(it)
            },
            onNavigation = {}
        )
        else -> LaunchedEffect(Unit){
            onNavigation(Screen.Login.route)
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreenLayout(state: HomeState, onAction: (HomeAction) -> Unit, onNavigation: (String) -> Unit){
    Scaffold {
        Column(
            modifier = Modifier
                .background(WhiteGray)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column() {
                    MainTitle(text = "Olá, usuário")
                    HeightSpacer(height = 4)
                    Subtitle(text = "O que você quer configurar hoje?")
                }
                Card(
                    elevation = 0.dp,
                    shape = RoundedCornerShape(100.dp),
                    backgroundColor = Primary,
                    contentColor = Color.White,
                    modifier = Modifier.size(40.dp)
                ) {
                    Row(modifier = Modifier.padding(8.dp)) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_menu),
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                onAction(HomeAction.Logout)
                            }
                        )
                    }
                }
            }
        }
    }
}