package com.example.data.repository

import com.example.data.api.ContentService
import com.example.data.model.loginresponse.LoginResponse
import com.example.data.model.loginresponse.UserBodyModel
import com.example.data.model.paymentsresponse.PaymentsResponse
import retrofit2.Response
import javax.inject.Inject

interface ContentRepository{
    suspend fun onLogin(userBody: UserBodyModel): Response<LoginResponse>

    suspend fun getPayments(token: String): Response<PaymentsResponse>
}

class ContentRepositoryImpl@Inject constructor(
    private val contentService: ContentService
): ContentRepository {
    override suspend fun onLogin(userBody: UserBodyModel): Response<LoginResponse> {
        return contentService.getMain(userBody = userBody)
    }

    override suspend fun getPayments(token: String): Response<PaymentsResponse> {
        return contentService.getPayments(token = token)
    }

}