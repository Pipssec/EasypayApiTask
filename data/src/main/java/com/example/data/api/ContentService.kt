package com.example.data.api

import com.example.data.model.loginresponse.LoginResponse
import com.example.data.model.paymentsresponse.PaymentsResponse
import com.example.data.model.loginresponse.UserBodyModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ContentService {

    companion object{
        const val APPKEY = "app-key"
        const val VERSION = "v"
        const val TOKEN = "token"
    }

    @POST("login")
    suspend fun getMain(
        @Header(APPKEY) appKey: String = "12345",
        @Header(VERSION) version: String = "1",
        @Body userBody: UserBodyModel
    ): Response<LoginResponse>

    @GET("payments")
    suspend fun getPayments(
        @Header(APPKEY) appKey: String = "12345",
        @Header(VERSION) version: String = "1",
        @Header(TOKEN) token: String,
    ): Response<PaymentsResponse>

}