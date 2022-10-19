package com.example.cinedrivein.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Distributor(
    val cnpj: String,
    @SerializedName("razaoSocial")
    val socialName: String
)