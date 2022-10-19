package com.example.cinedrivein.presentation.components.loader

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.cinedrivein.R
import com.example.cinedrivein.presentation.components.spacer.HeightSpacer
import com.example.cinedrivein.presentation.components.spacer.WidthSpacer
import com.example.cinedrivein.presentation.theme.Primary

@Composable
fun FullLoader(
    modifier: Modifier = Modifier,
    text: String? = null,
    color: Color = Primary
){
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Primary),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.fillMaxWidth(0.5f),
                painter = painterResource(id = R.drawable.logo_white),
                contentDescription = null
            )
            HeightSpacer(height = 24)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = color,
                    strokeWidth = 4.dp
                )
                WidthSpacer(width = 12)
                text?.let {
                    Text(text = it, color = color, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}