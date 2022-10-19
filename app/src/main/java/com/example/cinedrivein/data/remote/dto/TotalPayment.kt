package com.example.cinedrivein.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TotalPayment(
    @SerializedName("codigoModalidadePagamento")
    val typePayment: Int,
    @SerializedName("valorArrecadado")
    val totalValue: Double
)