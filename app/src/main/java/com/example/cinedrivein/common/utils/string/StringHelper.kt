package com.example.cinedrivein.common.utils.string

import com.example.cinedrivein.common.utils.validation.InputValidation

fun String.validateEmail(): InputValidation{
    val inputValidation = InputValidation()

    return if (this.trim().isEmpty()){
        inputValidation.copy(isValid = false, errorMessage = "* O e-mail é obrigatório")
    } else if (!this.contains(other = "@")){
        inputValidation.copy(isValid = false, errorMessage = "* E-mail inválido")
    } else {
        inputValidation.copy(isValid = true, errorMessage = null)
    }
}

fun String.validatePassword(): InputValidation {
    val inputValidation = InputValidation()

    return if (this.trim().isEmpty()){
        inputValidation.copy(isValid = false, errorMessage = "* A senha é obrigatório")
    } else if (this.length < 6){
        inputValidation.copy(isValid = false, errorMessage = "* Deve conter no mínimo 6 caracteres")
    } else {
        inputValidation.copy(isValid = true, errorMessage = null)
    }
}