package com.example.cinedrivein.presentation.feature.ancine.create.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinedrivein.R
import com.example.cinedrivein.presentation.feature.ancine.create.components.BottomBar
import com.example.cinedrivein.presentation.feature.ancine.create.model.CreateAncineEvents
import com.example.cinedrivein.presentation.feature.ancine.create.viewmodel.CreateAncineReportViewModel
import com.example.cinedrivein.presentation.theme.LightGray
import com.example.cinedrivein.presentation.theme.Primary
import org.koin.androidx.compose.getViewModel

@Composable
fun CreateAncineScreen(
    viewModel: CreateAncineReportViewModel = getViewModel()
){
    val state by remember { mutableStateOf(viewModel.state)}

    Scaffold(
        backgroundColor = LightGray,
        bottomBar = {
            BottomBar(sessionCount = state.value.sessionCount) {
                state.value.ancineRequest?.let {  ancineRequest ->
                    viewModel.viewEvent(CreateAncineEvents.SendReportEvent(ancineRequest))
                }
            }
        },
        topBar ={
            TopAppBar(
                backgroundColor = Primary,
                contentColor = Color.White,
                content = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = {},
                            content = {
                                Icon(
                                    tint = Color.White,
                                    painter = painterResource(id = R.drawable.ic_arrow_back),
                                    contentDescription = null
                                )
                            }
                        )
                        Text(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            text = stringResource(id = R.string.create_ancine_topbar_title),
                            color = Color.White
                        )
                    }
                }
            )
        }
    ) {
        LazyColumn(modifier = Modifier.padding(16.dp)){
            item {
                Card(
                    shape = RoundedCornerShape(5.dp),
                    backgroundColor = Color.White,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                modifier = Modifier.size(16.dp),
                                painter = painterResource(id = R.drawable.ic_session),
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Escolha a sala")
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                modifier = Modifier.size(16.dp),
                                painter = painterResource(id = R.drawable.ic_calendar),
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Escolha a data do relatório")
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier.weight(1.0f)
                            ) {
                                Text(text = "Houve sessão?")
                            }
                            Column(
                                modifier = Modifier.weight(1.0f)
                            ) {
                                Text(text = "Retificando?")
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            shape = RoundedCornerShape(5.dp),
                            enabled = true,
                            colors = ButtonDefaults.buttonColors(backgroundColor = Primary),
                            onClick = {
                                viewModel.viewEvent(
                                    CreateAncineEvents.IncrementSession(
                                        state.value.sessionCount.plus(1)
                                    )
                                )
                            },
                            content = {
                                Text(
                                    text = stringResource(id = R.string.btn_add_sessions),
                                    color = Color.White
                                )
                            }
                        )
                    }
                }
            }
        }

    }
}
