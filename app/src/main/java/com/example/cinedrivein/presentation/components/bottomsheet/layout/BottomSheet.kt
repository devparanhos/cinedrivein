package com.example.cinedrivein.presentation.components.bottomsheet.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.cinedrivein.R
import com.example.cinedrivein.presentation.components.bottomsheet.model.BottomSheetLayout
import com.example.cinedrivein.presentation.components.spacer.HeightSpacer
import com.example.cinedrivein.presentation.components.text.FollowUpText
import com.example.cinedrivein.presentation.theme.LightGray

@Composable
fun BuildBottomSheet(
    layout: BottomSheetLayout? = null,
    onDismiss: () -> Unit,
    onAction: ((Any?) -> Unit)? = null
){
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BottomSheetDrag()
        HeightSpacer(height = 24)
        when(layout){
            is BottomSheetLayout.AncineInfo -> AncineInfoBottomSheet()
            is BottomSheetLayout.RecoverPassword -> RecoverPasswordBottomSheet(){
                onAction?.invoke(it)
            }
            else -> EmptyBottomSheet()
        }
        HeightSpacer(height = 24)
        FollowUpText(text = stringResource(id = R.string.followup_close)) {
            onDismiss()
        }
    }
}

@Composable
fun BottomSheetDrag(){
    Card(
        shape = RoundedCornerShape(100.dp)
    ) {
        Spacer(modifier = Modifier
            .height(6.dp)
            .width(70.dp)
            .background(LightGray))
    }
}

