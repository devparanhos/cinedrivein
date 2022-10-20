package com.example.cinedrivein.presentation.feature.register.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.cinedrivein.R
import com.example.cinedrivein.presentation.components.button.FilledButton
import com.example.cinedrivein.presentation.components.input.InputEmail
import com.example.cinedrivein.presentation.components.input.InputPassword
import com.example.cinedrivein.presentation.components.spacer.HeightSpacer
import com.example.cinedrivein.presentation.components.text.MainTitle
import com.example.cinedrivein.presentation.components.text.Subtitle
import com.example.cinedrivein.presentation.feature.register.action.RegisterAction
import com.example.cinedrivein.presentation.feature.register.viewmodel.RegisterViewModel
import com.example.cinedrivein.presentation.navigation.Screen
import org.koin.androidx.compose.getViewModel

@Composable
fun BuildRegisterScreen(viewModel: RegisterViewModel = getViewModel(), onNavigation: (String) -> Unit) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                elevation = 0.dp
            ) {
                Row(modifier = Modifier.padding(8.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                onNavigation(Screen.Login.route)
                            }
                    )
                }
            }
        }
    ) {
        LazyColumn(modifier = Modifier.padding(16.dp)){
            item { 
                Column() {
                    MainTitle(text = stringResource(id = R.string.register_title_register))
                    HeightSpacer(height = 6)
                    Subtitle(text = stringResource(id = R.string.register_subtitle_register))
                }
            }
            item {
                HeightSpacer(height = 32)
                Column() {
                    InputEmail(
                        data = state.email,
                        imeAction = ImeAction.Next,
                        onChange = {
                            viewModel.submitAction(RegisterAction.UpdateEmail(it))
                        }
                    )
                    HeightSpacer(height = 24)
                    InputPassword(
                        data = state.password,
                        imeAction = ImeAction.Next,
                        onChange = {

                        }
                    )
                    HeightSpacer(height = 24)
                    InputPassword(
                        data = state.password,
                        canSeePassword = false,
                        placeholder = stringResource(id = R.string.register_placeholder_confirm_password) ,
                        imeAction = ImeAction.Next,
                        onChange = {

                        }
                    )
                    HeightSpacer(height = 32)
                    FilledButton(text = stringResource(id = R.string.buttons_register).uppercase()) {

                    }
                }
            }
        }

    }
}