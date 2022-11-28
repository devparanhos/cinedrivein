package com.example.cinedrivein.presentation.feature.movie.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinedrivein.domain.handler.RequestHandler
import com.example.cinedrivein.domain.model.movie.Movie
import com.example.cinedrivein.domain.usecase.movies.GetMoviesUseCase
import com.example.cinedrivein.presentation.feature.movie.list.action.MoviesAction
import com.example.cinedrivein.presentation.feature.movie.list.state.MoviesState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MoviesViewModel(
   private val getMoviesUseCase: GetMoviesUseCase
): ViewModel() {
    private val _state = MutableStateFlow(MoviesState())
    val state: StateFlow<MoviesState> = _state.asStateFlow()

    fun submitAction(action: MoviesAction){
        when(action){
            is MoviesAction.GetMovies -> getMovies()
            is MoviesAction.UpdateSearch -> _state.value = _state.value.copy(search = action.search)
        }
    }

    private fun getMovies(){
        _state.value = _state.value.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            getMoviesUseCase.getMovies(
                page = _state.value.page.toString(),
                onHandler = object : RequestHandler{
                    override fun onSuccess(data: Any?) {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            movies = data as List<Movie>
                        )
                    }

                    override fun onError(data: Any?, message: String?) {
                        _state.value = _state.value.copy(isLoading = false)
                    }
                }
            )
        }
    }
}