package com.example.cinedrivein.presentation.feature.distributors.list.action

sealed class DistributorsAction{
    object GetDistributors: DistributorsAction()

    class DeleteDistributor(val id: String): DistributorsAction()
}
