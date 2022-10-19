package com.example.cinedrivein.presentation.feature.login.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
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
import com.example.cinedrivein.presentation.components.button.FilledButton
import com.example.cinedrivein.presentation.components.input.InputEmail
import com.example.cinedrivein.presentation.components.input.InputPassword
import com.example.cinedrivein.presentation.components.loader.FullLoader
import com.example.cinedrivein.presentation.components.spacer.HeightSpacer
import com.example.cinedrivein.presentation.components.text.MainTitle
import com.example.cinedrivein.presentation.components.text.Subtitle
import com.example.cinedrivein.presentation.feature.login.action.LoginActions
import com.example.cinedrivein.presentation.feature.login.state.LoginState
import com.example.cinedrivein.presentation.feature.login.viewmodel.LoginViewModel
import com.example.cinedrivein.presentation.theme.Primary
import org.koin.androidx.compose.getViewModel

@Composable
fun BuildLoginScreen(viewModel: LoginViewModel = getViewModel()){
    val state by viewModel.state.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        when {
            state.isLoading -> FullLoader(color = Color.White, text = stringResource(id = R.string.label_verifying_access))
            else -> LoginScreenLayout(state = state) {
                viewModel.submitAction(it)
            }
        }
    }
}

@Composable
fun LoginScreenLayout(state: LoginState, onAction: (LoginActions) -> Unit) {
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
            .background(Primary.copy(alpha = 0.8f)),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .padding(24.dp)
                .weight(1f),
            painter = painterResource(id = R.drawable.logo_white),
            contentDescription = null
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2.5f),
            shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp, bottomEnd = 0.dp, bottomStart = 0.dp)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 32.dp)
            ) {
                MainTitle(text = stringResource(id = R.string.login_title_welcome))
                HeightSpacer(height = 6)
                Subtitle(text = stringResource(id = R.string.login_subtitle_login))
                HeightSpacer(height = 32)
                InputEmail(
                    data = state.email,
                    inputError = state.emailInputError,
                    onChange = {
                        onAction(LoginActions.UpdateEmail(it))
                    }
                )
                HeightSpacer(height = 24)
                InputPassword(
                    data = state.password,
                    inputError = state.passwordInputError,
                    onChange = {
                        onAction(LoginActions.UpdatePassword(it))
                    }
                )
                HeightSpacer(height = 32)
                FilledButton(
                    isRequesting = state.isRequesting,
                    isEnabled = !state.isRequesting,
                    text = stringResource(id = R.string.login_buttons_enter).uppercase()
                ) {
                    onAction(LoginActions.Login(email = state.email, password = state.password))
                }
            }
        }
    }
}