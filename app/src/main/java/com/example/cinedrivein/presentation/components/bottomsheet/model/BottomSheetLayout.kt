package com.example.cinedrivein.presentation.components.bottomsheet.model

sealed class BottomSheetLayout{
    object Empty: BottomSheetLayout()
    object AncineInfo: BottomSheetLayout()
}
