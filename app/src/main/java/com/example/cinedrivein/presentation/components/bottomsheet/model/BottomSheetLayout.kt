package com.example.cinedrivein.presentation.components.bottomsheet.model

sealed class BottomSheetLayout{
    object AncineInfo: BottomSheetLayout()
    object RecoverPassword: BottomSheetLayout()

    class DeleteData(val reference: String? = null, val data: String? = null): BottomSheetLayout()
}
