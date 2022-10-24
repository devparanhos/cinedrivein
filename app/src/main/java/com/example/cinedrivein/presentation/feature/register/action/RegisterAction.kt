package com.example.cinedrivein.presentation.feature.register.action

sealed class RegisterAction{
    data class UpdateName(val name: String): RegisterAction()
    data class UpdateEmail(val email: String):RegisterAction()
    data class UpdatePassword(val password: String): RegisterAction()
    data class UpdateConfirmPassword(val confirmPassword: String): RegisterAction()
    data class UpdateAncineNumber(val ancineNumber: String): RegisterAction()

    object ValidateInputs: RegisterAction()
}
