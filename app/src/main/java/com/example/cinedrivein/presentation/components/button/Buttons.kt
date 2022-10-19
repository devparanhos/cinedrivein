package com.example.cinedrivein.presentation.components.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.cinedrivein.presentation.theme.LightGray
import com.example.cinedrivein.presentation.theme.Primary

@Composable
fun FilledButton(
    modifier: Modifier = Modifier,
    isRequesting: Boolean = false,
    isEnabled: Boolean = true,
    text: String,
    onClick: () -> Unit
){
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp),
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Primary,
            contentColor = Color.White
        ),
        onClick = { onClick() }
    ) {
        when(isRequesting){
            true -> CircularProgressIndicator(modifier = Modifier.size(24.dp), color = LightGray)
            false -> Text(text = text)
        }
    }
}