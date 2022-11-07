package com.example.cinedrivein.presentation.components.bottomsheet.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.cinedrivein.R
import com.example.cinedrivein.presentation.components.button.FilledButton
import com.example.cinedrivein.presentation.components.spacer.HeightSpacer
import com.example.cinedrivein.presentation.components.text.Subtitle
import com.example.cinedrivein.presentation.components.text.Title
import com.example.cinedrivein.presentation.theme.Primary

@Composable
fun DeleteDataBottomSheet(reference: String? = null, onAction: () -> Unit){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(text = stringResource(id = R.string.title_delete_data))
        HeightSpacer(height = 16)
        Subtitle(
            text = stringResource(id = R.string.subtitle_delete_data, "o(a) $reference"),
            textAlign = TextAlign.Center
        )
        HeightSpacer(height = 24)
        FilledButton(
            text = stringResource(id = R.string.buttons_delete).uppercase(),
            backgroundColor = Primary
        ) {
            onAction()
        }

    }
}