package com.example.cinedrivein.presentation.feature.distributors.list.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinedrivein.R
import com.example.cinedrivein.domain.model.distributor.Distributor
import com.example.cinedrivein.presentation.components.bottomsheet.layout.BuildBottomSheet
import com.example.cinedrivein.presentation.components.bottomsheet.model.BottomSheetLayout
import com.example.cinedrivein.presentation.components.button.DefaultFloatingButton
import com.example.cinedrivein.presentation.components.button.FilledButtonSmall
import com.example.cinedrivein.presentation.components.loader.RequestingLoader
import com.example.cinedrivein.presentation.components.loader.ScreenLoading
import com.example.cinedrivein.presentation.components.spacer.HeightSpacer
import com.example.cinedrivein.presentation.components.spacer.ListDivider
import com.example.cinedrivein.presentation.components.spacer.WidthSpacer
import com.example.cinedrivein.presentation.components.topbar.DefaultTopbar
import com.example.cinedrivein.presentation.feature.distributors.list.action.DistributorsAction
import com.example.cinedrivein.presentation.feature.distributors.list.state.DistributorsState
import com.example.cinedrivein.presentation.feature.distributors.list.viewmodel.DistributorsViewModel
import com.example.cinedrivein.presentation.navigation.Screen
import com.example.cinedrivein.presentation.theme.Primary
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun BuildDistributorsScreen(
    viewModel: DistributorsViewModel = getViewModel(),
    onNavigation: (String) -> Unit
){
    val state by viewModel.state.collectAsState()
    viewModel.submitAction(DistributorsAction.GetDistributors)

    DistributorsScreenLayout(
        state = state,
        onAction = {
            viewModel.submitAction(it)
        },
        onNavigation = {
            onNavigation(it)
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DistributorsScreenLayout(state: DistributorsState, onAction: (DistributorsAction) -> Unit, onNavigation: (String) -> Unit){

    var layoutBottomSheet: BottomSheetLayout? by remember { mutableStateOf(null) }


    val coroutineScope = rememberCoroutineScope()
    val modalState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = {
            false
        }
    )

    ModalBottomSheetLayout(
        sheetState = modalState,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        sheetElevation = 5.dp,
        sheetContent = {
            BuildBottomSheet(
                layout = layoutBottomSheet,
                onAction = {
                    when(layoutBottomSheet){
                        is BottomSheetLayout.DeleteData -> {
                            onAction(DistributorsAction.DeleteDistributor(id = it as String))
                        }

                        else -> { }
                    }

                    coroutineScope.launch{
                        modalState.hide()
                    }
                },
                onDismiss = {
                    coroutineScope.launch{
                        modalState.hide()
                    }
                }
            )
        }
    ) {
        Scaffold(
            topBar = {
                DefaultTopbar(text = stringResource(id = R.string.topbar_title_distributors)) {
                    onNavigation(Screen.Menu.route)
                }
            },
            floatingActionButton = {
                if (state.distributors.isNotEmpty()) DefaultFloatingButton {

                }
            }
        ) {
            when {
                state.isLoading -> ScreenLoading()

                state.distributors.isEmpty() -> DistributorEmptyState{

                }

                else -> {
                    LazyColumn(modifier = Modifier.padding(16.dp)) {
                        itemsIndexed(state.distributors) { index, distributor ->
                            DistributorItem(
                                distributor = distributor,
                                onEdit = {

                                },
                                onDelete = {
                                    coroutineScope.launch {
                                        layoutBottomSheet = BottomSheetLayout.DeleteData(
                                            id = distributor.id,
                                            data = distributor.name
                                        )
                                        modalState.show()
                                    }
                                }
                            )
                            if (index != state.distributors.size - 1) {
                                ListDivider()
                            }
                        }
                    }

                    if (state.isRequesting){
                        RequestingLoader(text = stringResource(id = R.string.loading_deleting))
                    }
                }
            }
        }
    }

}

@Composable
fun DistributorEmptyState(onNavigation: (String) -> Unit){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(24.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.no_distributors),
                contentDescription = null,
                modifier = Modifier.size(110.dp)
            )
            HeightSpacer(height = 16)
            Text(
                text = stringResource(id = R.string.not_found_distributors),
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
            HeightSpacer(height = 24)
            FilledButtonSmall(
                text = stringResource(id = R.string.buttons_register).uppercase(),
                modifier = Modifier.fillMaxWidth(0.4f)
            ) {
                
            }
        }
    }
}

@Composable
fun DistributorItem(distributor: Distributor, onEdit: () -> Unit, onDelete: () -> Unit){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column() {
            Text(text = distributor.name)
            Text(text = distributor.cnpj, color = Color.LightGray)
        }

        Row() {
            Icon(
                painter = painterResource(id = R.drawable.ic_edit), 
                contentDescription = null,
                modifier = Modifier.clickable { 
                    onEdit()
                }
            )
            WidthSpacer(width = 8)
            Icon(
                painter = painterResource(id = R.drawable.ic_delete),
                contentDescription = null,
                modifier = Modifier.clickable {
                    onDelete()
                }
            )
        }
    }
}