package com.example.cinedrivein.presentation.feature.register.view

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.cinedrivein.R
import com.example.cinedrivein.presentation.components.bottomsheet.layout.BuildBottomSheet
import com.example.cinedrivein.presentation.components.bottomsheet.model.BottomSheetLayout
import com.example.cinedrivein.presentation.components.button.FilledButton
import com.example.cinedrivein.presentation.components.input.InputEmail
import com.example.cinedrivein.presentation.components.input.InputPassword
import com.example.cinedrivein.presentation.components.input.InputText
import com.example.cinedrivein.presentation.components.spacer.HeightSpacer
import com.example.cinedrivein.presentation.components.text.MainTitle
import com.example.cinedrivein.presentation.components.text.Subtitle
import com.example.cinedrivein.presentation.feature.register.action.RegisterAction
import com.example.cinedrivein.presentation.feature.register.state.RegisterState
import com.example.cinedrivein.presentation.feature.register.viewmodel.RegisterViewModel
import com.example.cinedrivein.presentation.navigation.Screen
import com.example.cinedrivein.presentation.theme.Primary
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BuildRegisterScreen(viewModel: RegisterViewModel = getViewModel(), onNavigation: (String) -> Unit) {

    val state by viewModel.state.collectAsState()

    if(state.user != null)
        LaunchedEffect(Unit){
            onNavigation(Screen.Home.route + "/" + state.user?.uid)
        }

    RegisterScreenLayout(
        state = state,
        onAction = {
            viewModel.submitAction(it)
        },
        onNavigation = {
            onNavigation(it)
        }
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RegisterScreenLayout(
    state: RegisterState,
    onAction: (RegisterAction) -> Unit,
    onNavigation: (String) -> Unit
){
    val scaffoldState = rememberScaffoldState()
    val focusManager = LocalFocusManager.current
    val coroutineScope = rememberCoroutineScope()
    val modalState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = {
            false
        }
    )

    var bottomSheetLayout: BottomSheetLayout? by remember { mutableStateOf(null) }

    when {
        state.hasRequestingError -> LaunchedEffect(Unit){
            coroutineScope.launch {
                scaffoldState.snackbarHostState.showSnackbar(
                    message = state.requestingError.toString()
                )
            }
        }
    }

    ModalBottomSheetLayout(
        sheetState = modalState,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        sheetElevation = 5.dp,
        sheetContent = {
            BuildBottomSheet(
                layout = bottomSheetLayout ,
                onDismiss = {
                    coroutineScope.launch {
                        modalState.hide()
                    }
                }
            )
        }
    ) {
        Scaffold(
            scaffoldState = scaffoldState,
            snackbarHost = {
                SnackbarHost(hostState = it){ data ->
                    Snackbar(snackbarData = data, backgroundColor = Primary)
                }
            },
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
            LazyColumn(
                modifier = Modifier.padding(16.dp)
            ){
                item {
                    MainTitle(text = stringResource(id = R.string.register_title_register))
                    HeightSpacer(height = 6)
                    Subtitle(text = stringResource(id = R.string.register_subtitle_register))
                    HeightSpacer(height = 32)
                    InputText(
                        placeholder = stringResource(id = R.string.placeholder_full_name),
                        leadingIcon = R.drawable.ic_account_circle,
                        inputError = state.nameInputError,
                        maxDigits = 20,
                        data = state.name,
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Text,
                        onChange = {
                            onAction(RegisterAction.UpdateName(it))
                        }
                    )
                    HeightSpacer(height = 24)
                    InputEmail(
                        data = state.email,
                        inputError = state.emailInputError,
                        imeAction = ImeAction.Next,
                        onChange = {
                            onAction(RegisterAction.UpdateEmail(it))
                        }
                    )
                    HeightSpacer(height = 24)
                    InputPassword(
                        data = state.password,
                        inputError = state.passwordInputError,
                        imeAction = ImeAction.Next,
                        onChange = {
                            onAction(RegisterAction.UpdatePassword(it))
                        }
                    )
                    HeightSpacer(height = 24)
                    InputPassword(
                        data = state.confirmPassword,
                        inputError = state.confirmPasswordInputError,
                        canSeePassword = false,
                        placeholder = stringResource(id = R.string.register_placeholder_confirm_password) ,
                        imeAction = ImeAction.Next,
                        onChange = {
                            onAction(RegisterAction.UpdateConfirmPassword(it))
                        }
                    )
                    HeightSpacer(height = 24)
                    InputText(
                        placeholder = stringResource(id = R.string.placeholder_ancine_nubmer),
                        inputError = state.ancineNumberInputError,
                        leadingIcon = R.drawable.ic_fact_check,
                        data = state.ancineNumber ,
                        imeAction = ImeAction.Done,
                        maxDigits = 5,
                        keyboardType = KeyboardType.Number,
                        trailingIcon = R.drawable.ic_info,
                        onChange = {
                            onAction(RegisterAction.UpdateAncineNumber(it))
                        },
                        onDone = {
                            focusManager.clearFocus()
                            onAction(RegisterAction.ValidateInputs)
                        },
                        onTrailing = {
                            focusManager.clearFocus()
                            bottomSheetLayout = BottomSheetLayout.AncineInfo
                            coroutineScope.launch {
                                modalState.show()
                            }
                        }
                    )
                    HeightSpacer(height = 32)
                    FilledButton(
                        text = stringResource(id = R.string.buttons_register).uppercase(),
                        isRequesting = state.isRequesting,
                        isEnabled = !state.isRequesting
                    ) {
                        focusManager.clearFocus()
                        onAction(RegisterAction.ValidateInputs)
                    }
                }
            }
        }
    }
}