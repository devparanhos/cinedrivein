package com.example.cinedrivein.common.utils.extensions

import com.example.cinedrivein.domain.model.distributor.Distributor

fun Distributor.toHash(): HashMap<String, String>{
    return hashMapOf(
        "name" to this.name,
        "socialName" to this.socialName,
        "cnpj" to this.cnpj,
        "id" to this.id.toString()
    )
}