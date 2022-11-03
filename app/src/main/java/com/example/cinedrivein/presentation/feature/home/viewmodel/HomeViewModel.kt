package com.example.cinedrivein.presentation.feature.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinedrivein.domain.handler.RequestHandler
import com.example.cinedrivein.domain.model.user.User
import com.example.cinedrivein.domain.usecase.GetUserUseCase
import com.example.cinedrivein.domain.usecase.LogoutUseCase
import com.example.cinedrivein.presentation.feature.home.action.HomeAction
import com.example.cinedrivein.presentation.feature.home.state.HomeState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getUserUseCase: GetUserUseCase
): ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    fun submitAction(action: HomeAction){
        when(action){
            is HomeAction.GetUser -> action.userUid?.let {
                getUser(userUid = it)
            }
        }
    }

    private fun getUser(userUid: String){
        viewModelScope.launch(Dispatchers.IO) {
            getUserUseCase.getUser(
                userUid = userUid,
                onHandler = object : RequestHandler{
                    override fun onSuccess(data: Any?) {
                        _state.value = _state.value.copy(
                            user = data as User,
                            isLoading = false
                        )
                    }

                    override fun onError(data: Any?, message: String?) {
                        TODO("Not yet implemented")
                    }
                }
            )
        }
    }
}