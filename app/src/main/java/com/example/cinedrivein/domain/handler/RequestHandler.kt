package com.example.cinedrivein.domain.handler

interface RequestHandler {
    fun onSuccess(data: Any?)
    fun onError(data: Any?, message: String?)
}