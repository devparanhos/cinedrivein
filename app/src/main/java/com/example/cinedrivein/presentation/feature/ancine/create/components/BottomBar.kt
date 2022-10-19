package com.example.cinedrivein.presentation.feature.ancine.create.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.cinedrivein.R
import com.example.cinedrivein.presentation.theme.Primary

@Composable
fun BottomBar(sessionCount: Int, onHandler: () -> Unit){
    Card(
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        backgroundColor = Color.White,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "${stringResource(id = R.string.label_sessions_registred)} $sessionCount")
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(5.dp),
                enabled = sessionCount > 0,
                colors = ButtonDefaults.buttonColors(backgroundColor = Primary),
                onClick = {
                    onHandler()
                },
                content = {
                    Text(
                        text = stringResource(id = R.string.btn_save_report),
                        color = Color.White
                    )
                }
            )
        }
    }
}