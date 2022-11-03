package com.example.cinedrivein.presentation.components.button

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinedrivein.presentation.components.spacer.WidthSpacer
import com.example.cinedrivein.presentation.theme.LightGray
import com.example.cinedrivein.presentation.theme.Primary

@Composable
fun FilledButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Primary,
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
            backgroundColor = backgroundColor,
            contentColor = Color.White
        ),
        onClick = { onClick() }
    ) {
        when(isRequesting){
            true -> CircularProgressIndicator(modifier = Modifier.size(24.dp), color = LightGray)
            false -> {
                Row() {
                    Text(text = text)
                }
            }
        }
    }
}

@Composable
fun FilledButtonSmall(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Primary,
    icon: Painter? = null,
    isRequesting: Boolean = false,
    isEnabled: Boolean = true,
    text: String,
    onClick: () -> Unit
){
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(46.dp),
        enabled = isEnabled,
        contentPadding = PaddingValues(8.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = Color.White
        ),
        onClick = { onClick() }
    ) {
        when(isRequesting){
            true -> CircularProgressIndicator(modifier = Modifier.size(24.dp), color = LightGray)
            false -> {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    icon?.let {
                        Icon(painter = it, contentDescription = null)
                        WidthSpacer(width = 8)
                    }
                    Text(text = text, overflow = TextOverflow.Ellipsis, maxLines = 1, fontSize = 12.sp)
                }
            }
        }
    }
}