package com.example.cinedrivein.presentation.components.menuitem

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinedrivein.R
import com.example.cinedrivein.presentation.components.spacer.WidthSpacer

@Composable
fun MenuItem(
    text: String,
    icon: Painter,
    hasArrow: Boolean = true,
    onClick: () -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.Bottom) {
            Icon(painter = icon, contentDescription = null, modifier = Modifier.size(24.dp))
            WidthSpacer(width = 8)
            Text(text = text, fontSize = 18.sp)
        }
        if (hasArrow){
            Icon(
                painter = painterResource(id = R.drawable.ic_small_arrow_right),
                contentDescription = null
            )
        }
    }
}

@Composable
fun MenuCard(
    text: String,
    image: Painter,
    onClick: () -> Unit
){
    Card(
        modifier = Modifier.height(70.dp).width(70.dp).clickable {
            onClick()
        }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(26.dp),
                painter = image,
                contentDescription = null
            )
            Text(text = text)
        }
    }
}