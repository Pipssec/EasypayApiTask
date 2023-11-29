package com.blogstour.app.util


sealed class AppException {
    data class NetworkException(val message: String) :
        AppException()

}
