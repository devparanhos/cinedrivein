package com.example.cinedrivein.presentation.feature.login.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.cinedrivein.R
import com.example.cinedrivein.presentation.components.button.FilledButton
import com.example.cinedrivein.presentation.components.input.InputEmail
import com.example.cinedrivein.presentation.components.input.InputPassword
import com.example.cinedrivein.presentation.components.loader.FullLoader
import com.example.cinedrivein.presentation.components.spacer.HeightSpacer
import com.example.cinedrivein.presentation.components.text.FollowUpText
import com.example.cinedrivein.presentation.components.text.MainTitle
import com.example.cinedrivein.presentation.components.text.Subtitle
import com.example.cinedrivein.presentation.feature.login.action.LoginActions
import com.example.cinedrivein.presentation.feature.login.state.LoginState
import com.example.cinedrivein.presentation.feature.login.viewmodel.LoginViewModel
import com.example.cinedrivein.presentation.navigation.Screen
import com.example.cinedrivein.presentation.theme.Primary
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BuildLoginScreen(viewModel: LoginViewModel = getViewModel(), onNavigation:(String) -> Unit){
    val state by viewModel.state.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        when {
            state.isLoading -> FullLoader(color = Color.White, text = stringResource(id = R.string.label_verifying_access))
            state.user != null -> LaunchedEffect(Unit){
                onNavigation(Screen.Home.route + "/" + state.user?.uid)
            }
            else -> LoginScreenLayout(
                state = state,
                onAction = { viewModel.submitAction(it) },
                onNavigation = {
                    onNavigation(it)
                }
            )
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreenLayout(state: LoginState, onAction: (LoginActions) -> Unit, onNavigation: (String) -> Unit) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = {
            SnackbarHost(hostState = it){ data ->
                Snackbar(snackbarData = data, backgroundColor = Primary)
            }
        }
    ) {
        if (state.hasRequestError){
            LaunchedEffect(Unit){
                coroutineScope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = state.messageError.toString()
                    )
                }
            }
        }

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
                        imeAction = ImeAction.Next,
                        inputError = state.emailInputError,
                        onChange = {
                            onAction(LoginActions.UpdateEmail(it))
                        }
                    )
                    HeightSpacer(height = 24)
                    InputPassword(
                        data = state.password,
                        imeAction = ImeAction.Done,
                        inputError = state.passwordInputError,
                        onChange = {
                            onAction(LoginActions.UpdatePassword(it))
                        },
                        onDone = {
                            focusManager.clearFocus()
                            onAction(LoginActions.Login(email = state.email, password = state.password))
                        }
                    )
                    HeightSpacer(height = 32)
                    FilledButton(
                        isRequesting = state.isRequesting,
                        isEnabled = !state.isRequesting,
                        text = stringResource(id = R.string.buttons_enter).uppercase()
                    ) {
                        onAction(LoginActions.Login(email = state.email, password = state.password))
                    }
                    HeightSpacer(height = 40)
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            FollowUpText(text = stringResource(id = R.string.login_followup_forgot_password)){

                            }
                            HeightSpacer(height = 12)
                            FollowUpText(text = stringResource(id = R.string.login_followup_register)){
                                onNavigation(Screen.Register.route)
                            }
                        }
                        FollowUpText(text = stringResource(id = R.string.login_followup_app_info), color = Primary){

                        }
                    }
                }
            }
        }
    }
}