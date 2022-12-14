package com.example.cinedrivein.presentation.components.text

import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinedrivein.presentation.theme.LightGray

@Composable
fun MainTitle(
    text: String,
    color: Color = Color.Black,
    maxlines: Int = 2,
){
    Text(
        text = text,
        color = color,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        maxLines = maxlines,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun Title(text: String, color: Color = Color.Black){
    Text(text = text, color = color, fontWeight = FontWeight.Normal, fontSize = 16.sp )
}

@Composable
fun Subtitle(
    text: String,
    textAlign: TextAlign? = TextAlign.Left,
    color: Color = LightGray,
    maxlines: Int = 2
){
    Text(
        text = text,
        color = color,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        textAlign = textAlign,
        maxLines = maxlines,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun TextError(text: String){
    Text(text = text, color = Color.Red, fontSize = 10.sp)
}

@Composable
fun FollowUpText(text: String, color: Color = LightGray, onFollowUp: (() -> Unit)? = null){
    Text(
        text = text,
        fontSize = 14.sp,
        color = color,
        modifier = Modifier.clickable {
            onFollowUp?.invoke()
        }
    )
}