package com.example.cinedrivein.common.utils.extensions

import com.example.cinedrivein.common.constants.CineDriveIn
import com.example.cinedrivein.common.utils.validation.InputValidation
import com.google.gson.Gson

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

fun String.validateName(): InputValidation{
    val inputValidation = InputValidation()

    return if (this.trim().isEmpty()){
        inputValidation.copy(isValid = false, errorMessage = "* O nome é obrigatório")
    } else if (this.trim().length <= 3){
        inputValidation.copy(isValid = false, errorMessage = "* O nome inserido é inválido")
    } else {
        inputValidation.copy(isValid = true, errorMessage = null)
    }
}

fun String.validateConfirmPassword(password: String): InputValidation{
    val inputValidation = InputValidation()

    return if (this.trim().isEmpty()){
        inputValidation.copy(isValid = false, errorMessage = "* Você precisa confirmar a senha")
    }else if (this.trim() != password){
        inputValidation.copy(isValid = false, errorMessage = "* As senhas não conferem")
    }else{
        inputValidation.copy(isValid = true, errorMessage = null)
    }
}

fun String.validateAncineNumber(): InputValidation{
    val inputValidation = InputValidation()

    return if (this.trim().isEmpty()){
        inputValidation.copy(isValid = false, errorMessage = "* O registro Ancine é obrigatório. Verifique o número com o exibidor")
    }else if (this.trim().length != 4){
        inputValidation.copy(isValid = false, errorMessage = "* O registro Ancine deve conter 4 dígitos")
    }else if (this.trim() != CineDriveIn.ANCINE_NUMBER){
        inputValidation.copy(isValid = false, errorMessage = "* O número Ancine informado é inválido")
    } else{
        inputValidation.copy(isValid = true, errorMessage = null)
    }
}

fun <A> String.fromJson(type: Class<A>): A {
    return Gson().fromJson(this, type)
}

fun <A> A.toJson(): String? {
    return Gson().toJson(this)
}