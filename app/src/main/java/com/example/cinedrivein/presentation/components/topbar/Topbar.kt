package com.example.cinedrivein.presentation.components.topbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinedrivein.R
import com.example.cinedrivein.presentation.components.input.InputText
import com.example.cinedrivein.presentation.components.input.InputTextDefault
import com.example.cinedrivein.presentation.components.spacer.HeightSpacer
import com.example.cinedrivein.presentation.components.spacer.WidthSpacer
import com.example.cinedrivein.presentation.theme.Primary

@Composable
fun DefaultTopbar(
    text: String,
    backgroundColor: Color = Primary,
    onClick: () -> Unit
){
    TopAppBar(
        backgroundColor = backgroundColor,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.clickable { 
                onClick()
            }
        )
        WidthSpacer(width = 16)
        Text(text = text, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
    }
}
