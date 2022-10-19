package com.example.cinedrivein.presentation.components.text

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.cinedrivein.presentation.theme.LightGray

@Composable
fun MainTitle(text: String, color: Color = Color.Black){
    Text(text = text, color = color, fontWeight = FontWeight.Bold, fontSize = 18.sp )
}

@Composable
fun Subtitle(text: String){
    Text(text = text, color = LightGray, fontWeight = FontWeight.Normal, fontSize = 16.sp )
}

@Composable
fun TextError(text: String){
    Text(text = text, color = Color.Red, fontSize = 10.sp)
}