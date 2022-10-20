package com.example.cinedrivein.presentation.components.text

import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinedrivein.presentation.theme.LightGray

@Composable
fun MainTitle(text: String, color: Color = Color.Black){
    Text(text = text, color = color, fontWeight = FontWeight.Bold, fontSize = 18.sp )
}

@Composable
fun Subtitle(text: String){
    Text(
        text = text,
        color = LightGray,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    )
}

@Composable
fun TextError(text: String){
    Text(text = text, color = Color.Red, fontSize = 10.sp)
}

@Composable
fun FollowUpText(text: String, color: Color = LightGray, onFollowUp: () -> Unit){
    Text(
        text = text,
        fontSize = 14.sp,
        color = color,
        modifier = Modifier.clickable {
            onFollowUp()
        }
    )
}