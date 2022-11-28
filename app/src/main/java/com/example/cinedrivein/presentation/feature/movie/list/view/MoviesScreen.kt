package com.example.cinedrivein.presentation.feature.movie.list.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.cinedrivein.R
import com.example.cinedrivein.presentation.components.button.DefaultFloatingButton
import com.example.cinedrivein.presentation.components.input.InputSearch
import com.example.cinedrivein.presentation.components.loader.ScreenLoading
import com.example.cinedrivein.presentation.components.movie.MovieCard
import com.example.cinedrivein.presentation.components.topbar.DefaultTopbar
import com.example.cinedrivein.presentation.feature.movie.list.action.MoviesAction
import com.example.cinedrivein.presentation.feature.movie.list.state.MoviesState
import com.example.cinedrivein.presentation.feature.movie.list.viewmodel.MoviesViewModel
import com.example.cinedrivein.presentation.navigation.Screen
import com.example.cinedrivein.presentation.theme.Primary
import org.koin.androidx.compose.getViewModel

@Composable
fun BuildMoviesScreen(
    viewModel: MoviesViewModel = getViewModel(),
    onNavigation: (String) -> Unit
){
    val state by viewModel.state.collectAsState()

    MoviesScreenLayout(
        state = state,
        onAction = {
            viewModel.submitAction(action = it)
        },
        onNavigation = {
            onNavigation(it)
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MoviesScreenLayout(state: MoviesState, onAction: (MoviesAction) -> Unit, onNavigation: (String) -> Unit){
    Scaffold(
        topBar = {
            DefaultTopbar(text = stringResource(id = R.string.topbar_title_movies)){
                onNavigation(Screen.Return.route)
            }
        },
        floatingActionButton = {
            if (state.movies.isNotEmpty()) DefaultFloatingButton {

            }
        }
    ){
        Column() {
            Row(modifier = Modifier
                .background(color = Primary)
                .padding(16.dp)) {
                InputSearch(
                    enabled = !state.isLoading,
                    data = state.search,
                    placeholder = stringResource(id = R.string.placeholder_search_movie),
                    onChange = {
                        onAction(MoviesAction.UpdateSearch(it))
                    }
                )
            }

            when{
                state.isLoading -> {
                    ScreenLoading()
                    onAction(MoviesAction.GetMovies)
                }

                state.movies.isEmpty() -> {
                    Text(text = "sem filmes")
                }

                else -> {
                    LazyVerticalGrid(
                        contentPadding = PaddingValues(16.dp),
                        columns = GridCells.Fixed(2),
                        verticalArrangement = Arrangement.spacedBy(14.dp),
                        horizontalArrangement = Arrangement.spacedBy(14.dp),
                        content = {
                            items(state.movies){ movie ->
                                MovieCard(image = movie.image)
                            }
                        }
                    )
                }
            }
        }
    }
}