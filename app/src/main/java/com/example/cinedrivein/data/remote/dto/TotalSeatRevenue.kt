package com.example.cinedrivein.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TotalSeatRevenue(
    @SerializedName("codigoCategoriaIngresso")
    val typeTicket: String,
    @SerializedName("quantidadeEspectadores")
    val totalTicketSold: Int,
    @SerializedName("totalizacoesModalidadePagamento")
    val totalPayments: List<TotalPayment>
)