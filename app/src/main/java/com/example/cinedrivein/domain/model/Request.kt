package com.example.cinedrivein.domain.model

sealed class Request(val data: Any?, val message: String? = null){
    class Success(data: Any?): Request(data)
    class Error(data: Any?, message: String?): Request(data, message)
}
