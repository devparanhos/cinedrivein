package com.example.cinedrivein.presentation.components.bottomsheet.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.cinedrivein.R
import com.example.cinedrivein.presentation.components.spacer.HeightSpacer
import com.example.cinedrivein.presentation.components.text.Subtitle
import com.example.cinedrivein.presentation.components.text.Title

@Composable
fun AncineInfoBottomSheet(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(text = stringResource(id = R.string.title_ancine_info))
        HeightSpacer(height = 16)
        Subtitle(
            text = stringResource(id = R.string.subtitle_ancine_number_explination),
            textAlign = TextAlign.Center
        )
    }
}