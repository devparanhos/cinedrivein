package com.example.cinedrivein.presentation.components.bottomsheet.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import com.example.cinedrivein.R
import com.example.cinedrivein.common.utils.extensions.validateEmail
import com.example.cinedrivein.presentation.components.button.FilledButton
import com.example.cinedrivein.presentation.components.input.InputEmail
import com.example.cinedrivein.presentation.components.spacer.HeightSpacer
import com.example.cinedrivein.presentation.components.text.Subtitle
import com.example.cinedrivein.presentation.components.text.Title

@Composable
fun RecoverPasswordBottomSheet(onAction:(String) -> Unit) {
    var email by remember { mutableStateOf("") }
    var emailError: String? by remember { mutableStateOf(null) }
    val focusManager = LocalFocusManager.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(text = stringResource(id = R.string.title_recover_password))
        HeightSpacer(height = 16)
        Subtitle(
            text = stringResource(id = R.string.subtitle_recover_password),
            textAlign = TextAlign.Center
        )
        HeightSpacer(height = 24)
        InputEmail(
            data = email,
            inputError = emailError,
            imeAction = ImeAction.Done,
            onChange = {
                email = it
            },
            onDone = {
                focusManager.clearFocus()
                when(email.validateEmail().isValid){
                    true -> {
                        onAction(email)
                        email = ""
                    }
                    false -> emailError = email.validateEmail().errorMessage
                }
            }
        )
        HeightSpacer(height = 16)
        FilledButton(text = stringResource(id = R.string.buttons_send_email).uppercase()) {
            focusManager.clearFocus()
            when(email.validateEmail().isValid){
                true -> {
                    onAction(email)
                    email = ""
                }

                false -> emailError = email.validateEmail().errorMessage
            }
        }
    }
}