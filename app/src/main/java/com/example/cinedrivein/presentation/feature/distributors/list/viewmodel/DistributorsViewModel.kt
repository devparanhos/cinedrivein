package com.example.cinedrivein.presentation.feature.distributors.list.viewmodel

import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinedrivein.domain.handler.RequestHandler
import com.example.cinedrivein.domain.model.distributor.Distributor
import com.example.cinedrivein.domain.usecase.distributors.DeleteDistributorUseCase
import com.example.cinedrivein.domain.usecase.distributors.GetDistributorsUseCase
import com.example.cinedrivein.presentation.feature.distributors.list.action.DistributorsAction
import com.example.cinedrivein.presentation.feature.distributors.list.state.DistributorsState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@Suppress("UNCHECKED_CAST")
class DistributorsViewModel(
    private val getDistributorsUseCase: GetDistributorsUseCase,
    private val deleteDistributorUseCase: DeleteDistributorUseCase
): ViewModel() {
    private val _state = MutableStateFlow(DistributorsState())
    val state: StateFlow<DistributorsState> = _state

    fun submitAction(action: DistributorsAction){
        when(action){
            is DistributorsAction.GetDistributors -> getDistributors()
            is DistributorsAction.DeleteDistributor -> deleteDistributor(reference = action.reference)
        }
    }

    private fun getDistributors(){
        viewModelScope.launch(Dispatchers.IO) {
            getDistributorsUseCase.getDistributors(
                onHandler = object : RequestHandler{
                    override fun onSuccess(data: Any?) {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            distributors = data as List<Distributor>
                        )
                    }

                    override fun onError(data: Any?, message: String?) {

                    }
                }
            )
        }
    }

    private fun deleteDistributor(reference: String){
        _state.value = _state.value.copy(isRequesting = true)
        viewModelScope.launch(Dispatchers.IO) {
            deleteDistributorUseCase.deleteDistributor(
                reference = reference,
                onHandler = object : RequestHandler{
                    override fun onSuccess(data: Any?) {
                        _state.value = _state.value.copy(isRequesting = false)
                        submitAction(DistributorsAction.GetDistributors)
                    }

                    override fun onError(data: Any?, message: String?) {
                        _state.value = _state.value.copy(isRequesting = false)
                    }
                }
            )
        }
    }
}