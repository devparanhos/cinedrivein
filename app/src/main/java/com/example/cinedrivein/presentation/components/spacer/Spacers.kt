package com.example.cinedrivein.presentation.components.spacer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HeightSpacer(height: Int){
    Spacer(modifier = Modifier.height(height.dp))
}

@Composable
fun WidthSpacer(width: Int){
    Spacer(modifier = Modifier.width(width.dp))
}

@Composable
fun ListDivider(distance: Int = 8){
    HeightSpacer(height = distance)
    Spacer(modifier = Modifier
        .height(1.dp)
        .fillMaxWidth()
        .background(Color.LightGray))
    HeightSpacer(height = distance)
}