package com.example.cinedrivein.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SeatSale(
    @SerializedName("codigoTipoAssento")
    val typeSeat: String,
    @SerializedName("quantidadeDisponibilizada")
    val totalSeatsSold: Int,
    @SerializedName("totalizacoesCategoriaIngresso")
    val totalSeatsRevenues: List<TotalSeatRevenue>
)