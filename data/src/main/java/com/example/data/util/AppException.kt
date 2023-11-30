package com.example.data.util


sealed class AppException {
    data class NetworkException(val message: String) :
        AppException()

}
