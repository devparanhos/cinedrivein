package com.example.cinedrivein.presentation.feature.ancine.create.model

data class CreateAncineFields(
    var hasSession: Boolean = true,
    var isRectifying: Boolean = false,
    var date: String = "",
    var room: String = ""
){
    companion object{
        val EMPTY = CreateAncineFields
    }
}
