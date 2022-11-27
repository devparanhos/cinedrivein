package com.example.cinedrivein.presentation.feature.distributors.create.state

import com.example.cinedrivein.domain.model.distributor.Distributor

data class CreateDistributorState(
    val name: String = "",
    val socialName: String = "",
    val cnpj: String = "",
    val distributor: Distributor? = null,
    val nameError: String? = "",
    val socialNameError: String? = "",
    val cnpjError: String? = "",
    val isRequesting: Boolean = false,
    val isDistributorCreated: Boolean = false
)
