package com.pavellukyanov.themartiancompose.util

class ServerException(exceptionCode: Int, message: String? = null) : Exception("Code: $exceptionCode Message: $message")