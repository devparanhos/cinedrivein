package com.example.cinedrivein.presentation.feature.distributors.create.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.cinedrivein.R
import com.example.cinedrivein.domain.model.distributor.Distributor
import com.example.cinedrivein.presentation.components.button.FilledButton
import com.example.cinedrivein.presentation.components.input.InputCnpj
import com.example.cinedrivein.presentation.components.input.InputTextDefault
import com.example.cinedrivein.presentation.components.loader.ScreenLoading
import com.example.cinedrivein.presentation.components.spacer.HeightSpacer
import com.example.cinedrivein.presentation.components.text.Subtitle
import com.example.cinedrivein.presentation.components.topbar.DefaultTopbar
import com.example.cinedrivein.presentation.feature.distributors.create.action.CreateDistributorAction
import com.example.cinedrivein.presentation.feature.distributors.create.state.CreateDistributorState
import com.example.cinedrivein.presentation.feature.distributors.create.viewmodel.CreateDistributorViewModel
import com.example.cinedrivein.presentation.navigation.Screen
import com.example.cinedrivein.presentation.theme.DarkBlack
import org.koin.androidx.compose.getViewModel

@Composable
fun BuildCreateDistributor(
    distributor: Distributor?,
    viewModel: CreateDistributorViewModel = getViewModel(),
    onNavigation: (String) -> Unit
){
    val state by viewModel.state.collectAsState()

    CreateDistributorScreenLayout(
        distributor = distributor,
        state = state,
        onNavigation = {
            onNavigation(it)
        },
        onAction = {
            viewModel.submitAction(it)
        }
    )
}

@Composable
fun CreateDistributorScreenLayout(
    distributor: Distributor?,
    state: CreateDistributorState,
    onNavigation: (String) -> Unit,
    onAction: (CreateDistributorAction) -> Unit
){
    val title = if (state.distributor == null) R.string.topbar_title_create_distributor else R.string.topbar_title_update_distributor
    val button = if (state.distributor == null) R.string.buttons_register else R.string.buttons_update
    val focusManager = LocalFocusManager.current

    if (state.isDistributorCreated || state.isDistributorUpdated){
        LaunchedEffect(Unit){
            onNavigation(Screen.Distributors.route)
        }
    }

    Scaffold(
        topBar = {
            DefaultTopbar(text = stringResource(id = title) ) {
                onNavigation(Screen.Distributors.route)
            }
        }
    ) {
        when{
            state.isLoading -> {
                ScreenLoading()
                onAction(CreateDistributorAction.UpdateDistributor(distributor = distributor))
            }

            else -> {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Subtitle(
                        text = stringResource(id = R.string.subtitle_create_distributor),
                        color = DarkBlack
                    )
                    HeightSpacer(height = 24)
                    InputTextDefault(
                        placeholder = stringResource(id = R.string.placeholder_name),
                        inputError = state.nameError,
                        data = state.name,
                        imeAction = ImeAction.Next ,
                        keyboardType = KeyboardType.Text,
                        onChange = {
                            onAction(CreateDistributorAction.UpdateName(it))
                        }
                    )
                    HeightSpacer(height = 8)
                    InputTextDefault(
                        placeholder = stringResource(id = R.string.placeholder_social_name),
                        inputError = state.socialNameError,
                        data = state.socialName,
                        imeAction = ImeAction.Next ,
                        keyboardType = KeyboardType.Text,
                        onChange = {
                            onAction(CreateDistributorAction.UpdateSocialName(it))
                        }
                    )
                    HeightSpacer(height = 8)
                    InputCnpj(
                        placeholder = stringResource(id = R.string.placeholder_cnpj),
                        inputError = state.cnpjError,
                        data = state.cnpj,
                        imeAction = ImeAction.Done ,
                        keyboardType = KeyboardType.Number,
                        maxDigits = 18,
                        onChange = {
                            onAction(CreateDistributorAction.UpdateCnpj(it))
                        },
                        onDone = {
                            focusManager.clearFocus()
                            onAction(CreateDistributorAction.ValidateForm(isUpdating = state.distributor != null))
                        }
                    )
                    HeightSpacer(height = 16)
                    FilledButton(
                        text = stringResource(id = button).uppercase(),
                        isRequesting = state.isRequesting,
                        isEnabled = !state.isRequesting,
                    ) {
                        focusManager.clearFocus()
                        onAction(CreateDistributorAction.ValidateForm(isUpdating = state.distributor != null))
                    }
                }
            }
        }
    }
}