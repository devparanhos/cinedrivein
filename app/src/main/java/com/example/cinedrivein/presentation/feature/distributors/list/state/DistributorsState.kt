package com.example.cinedrivein.presentation.feature.distributors.list.state

import com.example.cinedrivein.domain.model.distributor.Distributor

data class DistributorsState(
    val isLoading: Boolean = true,
    val distributors: List<Distributor> = listOf(),
    val isRequesting: Boolean = false
)