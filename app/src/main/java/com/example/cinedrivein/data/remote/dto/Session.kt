package com.example.cinedrivein.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Session(
    @SerializedName("dataHoraInicio")
    val fullHour: String,
    @SerializedName("modalidade")
    val type: String,
    @SerializedName("obras")
    val movies: List<Movie>,
    @SerializedName("totalizacoesTipoAssento")
    val seatsSales: List<SeatSale>,
    @SerializedName("vendedorRemoto")
    val cinema: Cinema
)