package com.example.cinedrivein.presentation.feature.menu.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.cinedrivein.R
import com.example.cinedrivein.domain.model.user.User
import com.example.cinedrivein.presentation.components.button.FilledButtonSmall
import com.example.cinedrivein.presentation.components.menuitem.MenuItem
import com.example.cinedrivein.presentation.components.spacer.HeightSpacer
import com.example.cinedrivein.presentation.components.spacer.ListDivider
import com.example.cinedrivein.presentation.components.spacer.WidthSpacer
import com.example.cinedrivein.presentation.components.text.MainTitle
import com.example.cinedrivein.presentation.components.text.Subtitle
import com.example.cinedrivein.presentation.feature.menu.action.MenuAction
import com.example.cinedrivein.presentation.feature.menu.state.MenuState
import com.example.cinedrivein.presentation.feature.menu.viewmodel.MenuViewModel
import com.example.cinedrivein.presentation.navigation.Screen
import com.example.cinedrivein.presentation.theme.DarkBlack
import com.example.cinedrivein.presentation.theme.Primary
import org.koin.androidx.compose.getViewModel

@Composable
fun BuildMenuScreen(
    user: User,
    viewModel: MenuViewModel = getViewModel(),
    onNavigation: (String) -> Unit
){
    val state by viewModel.state.collectAsState()
    viewModel.submitAction(MenuAction.UpdateUser(user = user))

    when {
        !state.isLogged -> LaunchedEffect(Unit) {
            onNavigation(Screen.Login.route)
        }

        else -> MenuScreenLayout(
            state = state,
            onAction = {
                viewModel.submitAction(it)
            },
            onNavigation = {
                onNavigation(it)
            }
        )
    }
}

@Composable
fun MenuScreenLayout(state: MenuState, onAction:(MenuAction) -> Unit, onNavigation: (String) -> Unit){
    Scaffold{
        Box(modifier = Modifier.fillMaxSize()){
            Image(
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.movies_poster),
                contentDescription = null
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Primary.copy(alpha = 0.8f))
        ){
            Column(
                modifier = Modifier.padding(16.dp).weight(1.0f)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            onNavigation(Screen.Home.route)
                        }
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    Column(
                        modifier = Modifier.weight(1.0f).fillMaxHeight(),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.Start
                    ) {
                        state.user?.let { user ->
                            HeightSpacer(height = 24)
                            MainTitle(text = user.name, color = Color.White, maxlines = 1)
                            HeightSpacer(height = 4)
                            Subtitle(text = user.email, color = Color.White, maxlines = 1)
                        }
                    }
                    WidthSpacer(width = 16)
                    Row(
                        modifier = Modifier.weight(1.0f).fillMaxHeight(),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        FilledButtonSmall(
                            text = stringResource(id = R.string.buttons_change_password).uppercase(),
                            backgroundColor = DarkBlack,
                            icon = painterResource(id = R.drawable.ic_lock)
                        ) {

                        }
                    }
                }

            }
            Column(
                modifier = Modifier.weight(4.0f)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp, bottomEnd = 0.dp, bottomStart = 0.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(vertical = 24.dp, horizontal = 16.dp)
                            .fillMaxHeight()
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        LazyColumn{
                            item {
                                MenuItem(
                                    text = stringResource(id = R.string.followup_distributor),
                                    icon = painterResource(id = R.drawable.ic_distributor),
                                    hasArrow = true
                                ) {
                                    onNavigation(Screen.Distributors.route)
                                }
                                ListDivider(distance = 16)
                                MenuItem(
                                    text = stringResource(id = R.string.followup_movies),
                                    icon = painterResource(id = R.drawable.ic_movie),
                                    hasArrow = true
                                ) {
                                    onNavigation(Screen.Movies.route)
                                }
                                ListDivider(distance = 16)
                                MenuItem(
                                    text = stringResource(id = R.string.followup_prices),
                                    icon = painterResource(id = R.drawable.ic_price),
                                    hasArrow = true
                                ) {

                                }
                                ListDivider(distance = 16)
                                MenuItem(
                                    text = stringResource(id = R.string.followup_sessions),
                                    icon = painterResource(id = R.drawable.ic_time),
                                    hasArrow = true
                                ) {

                                }
                                ListDivider(distance = 16)
                                MenuItem(
                                    text = stringResource(id = R.string.followup_report),
                                    icon = painterResource(id = R.drawable.ic_report),
                                    hasArrow = true
                                ) {

                                }
                            }
                        }
                        MenuItem(
                            text = stringResource(id = R.string.followup_exit_app),
                            icon = painterResource(id = R.drawable.ic_exit),
                            hasArrow = false
                        ) {
                            onAction(MenuAction.Logout)
                        }
                    }
                }
            }
        }
    }
}