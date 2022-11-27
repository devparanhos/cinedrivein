package com.example.cinedrivein.presentation.feature.distributors.create.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinedrivein.common.utils.extensions.validateCnpj
import com.example.cinedrivein.common.utils.extensions.validateText
import com.example.cinedrivein.domain.handler.RequestHandler
import com.example.cinedrivein.domain.model.distributor.Distributor
import com.example.cinedrivein.domain.usecase.distributors.CreateDistributorUseCase
import com.example.cinedrivein.presentation.feature.distributors.create.action.CreateDistributorAction
import com.example.cinedrivein.presentation.feature.distributors.create.state.CreateDistributorState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CreateDistributorViewModel(
    private val createDistributorUseCase: CreateDistributorUseCase
): ViewModel() {
    private val _state = MutableStateFlow(CreateDistributorState())
    val state: StateFlow<CreateDistributorState> = _state

    fun submitAction(action: CreateDistributorAction){
        when(action){
            is CreateDistributorAction.UpdateName -> _state.value = _state.value.copy(name = action.name, nameError = "")
            is CreateDistributorAction.UpdateSocialName -> _state.value = _state.value.copy(socialName = action.socialName, socialNameError = "")
            is CreateDistributorAction.UpdateCnpj -> _state.value = _state.value.copy(cnpj = action.cnpj, cnpjError = "")
            is CreateDistributorAction.UpdateDistributor -> updateDistributorState(action.distributor)
            is CreateDistributorAction.ValidateForm -> validateForm(isUpdating = action.isUpdating)
        }
    }

    private fun updateDistributorState(distributor: Distributor?){
        distributor?.let {
            _state.value = _state.value.copy(
                distributor = it,
                name = it.name,
                socialName = it.socialName,
                cnpj = it.cnpj
            )
        }
    }

    private fun validateForm(isUpdating: Boolean){
        _state.value = _state.value.copy(isRequesting = true)
        when{
            state.value.name.validateText(prefix = "O nome").isValid &&
            state.value.cnpj.validateCnpj().isValid &&
            state.value.socialName.validateText(prefix = "A razão social").isValid -> {
                val distributor = Distributor(
                    name = state.value.name,
                    socialName = state.value.socialName,
                    cnpj = state.value.cnpj,
                    id = state.value.distributor?.id
                )

                if (isUpdating) updateDistributor(distributor = distributor) else createDistributor(distributor = distributor)
            }

            else -> {
                _state.value = _state.value.copy(
                    isRequesting = false,
                    nameError = state.value.name.validateText(prefix = "O nome").errorMessage,
                    socialNameError = state.value.socialName.validateText(prefix = "A razão social").errorMessage,
                    cnpjError = state.value.cnpj.validateCnpj().errorMessage
                )
            }
        }
    }

    private fun updateDistributor(distributor: Distributor){

    }

    private fun createDistributor(distributor: Distributor){
        viewModelScope.launch(Dispatchers.IO) {
            createDistributorUseCase.createDistributor(
                distributor = distributor,
                onHandler = object : RequestHandler{
                    override fun onSuccess(data: Any?) {
                        _state.value = _state.value.copy(isDistributorCreated = true)
                    }

                    override fun onError(data: Any?, message: String?) {
                        Log.i("Teste", "teste")
                    }
                }
            )
        }
    }
}