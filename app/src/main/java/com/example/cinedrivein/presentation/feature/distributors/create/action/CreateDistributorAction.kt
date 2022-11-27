package com.example.cinedrivein.presentation.feature.distributors.create.action

import com.example.cinedrivein.domain.model.distributor.Distributor

sealed class CreateDistributorAction{
    class UpdateName(val name: String):CreateDistributorAction()
    class UpdateSocialName(val socialName: String):CreateDistributorAction()
    class UpdateCnpj(val cnpj: String):CreateDistributorAction()
    class ValidateForm(val isUpdating: Boolean):CreateDistributorAction()
    class UpdateDistributor(val distributor: Distributor?):CreateDistributorAction()
}
