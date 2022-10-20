package com.example.cinedrivein.presentation.components.input

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.constraintlayout.widget.Placeholder
import com.example.cinedrivein.R
import com.example.cinedrivein.presentation.components.spacer.HeightSpacer
import com.example.cinedrivein.presentation.components.text.TextError
import com.example.cinedrivein.presentation.theme.LightGray

@Composable
fun InputEmail(
    modifier: Modifier = Modifier,
    inputError: String? = null,
    data: String,
    imeAction: ImeAction,
    onChange: (String) -> Unit,
    onDone: (() -> Unit)? = null
){
    Column() {
        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            maxLines = 1,
            value = data,
            placeholder = {
                Text(text = stringResource(id = R.string.login_placeholder_email))
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = LightGray,
                cursorColor = LightGray
            ),
            leadingIcon = {
                Icon(
                    tint = LightGray,
                    painter = painterResource(id = R.drawable.ic_email),
                    contentDescription = null
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = imeAction
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (onDone != null) {
                        onDone()
                    }
                }
            ),
            onValueChange = {
                onChange(it)
            }
        )
        inputError?.let{
            HeightSpacer(height = 2)
            TextError(text = it)
        }
    }
}

@Composable
fun InputPassword(
    modifier: Modifier = Modifier,
    inputError: String? = null,
    canSeePassword: Boolean = true,
    placeholder: String? = null,
    data: String,
    imeAction: ImeAction,
    onChange: (String) -> Unit,
    onDone: (() -> Unit)? = null
){
    var icon by remember { mutableStateOf(R.drawable.ic_eye_off) }
    var isPasswordVisible by remember { mutableStateOf(false) }

    Column() {
        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            maxLines = 1,
            value = data,
            placeholder = {
                Text(text = placeholder ?: stringResource(id = R.string.login_placeholder_password))
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = LightGray,
                cursorColor = LightGray
            ),
            trailingIcon = {
                if (canSeePassword){
                    Icon(
                        tint = LightGray,
                        painter = painterResource(id = icon),
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            isPasswordVisible = !isPasswordVisible
                            icon = if (isPasswordVisible) R.drawable.ic_eye else R.drawable.ic_eye_off
                        }
                    )
                }
            },
            leadingIcon = {
                Icon(
                    tint = LightGray,
                    painter = painterResource(id = R.drawable.ic_lock),
                    contentDescription = null
                )
            },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation() ,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = imeAction
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (onDone != null) {
                        onDone()
                    }
                }
            ),
            onValueChange = {
                onChange(it)
            }
        )
        inputError?.let{
            HeightSpacer(height = 2)
            TextError(text = it)
        }
    }
}