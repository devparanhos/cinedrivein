package com.example.cinedrivein.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Movie(
    val audio: String,
    @SerializedName("audioDescricao")
    val hasAudioDescription: String,
    @SerializedName("digital")
    val isDigital: String,
    @SerializedName("distribuidor")
    val distributor: Distributor,
    @SerializedName("legenda")
    val hasSubtitles: String,
    @SerializedName("legendagemDescritiva")
    val hasDescriptionSubtitles: String,
    @SerializedName("libras")
    val hasSignLanguage: String,
    @SerializedName("numeroObra")
    val movieId: String,
    @SerializedName("tipoProjecao")
    val typeProjection: String,
    @SerializedName("tipoTela")
    val typeScreen: String,
    @SerializedName("tituloObra")
    val movieTitle: String
)