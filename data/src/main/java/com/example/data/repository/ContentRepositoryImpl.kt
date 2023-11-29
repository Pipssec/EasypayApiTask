package com.example.data.repository

import com.example.data.api.ContentService
import com.example.data.model.loginresponse.LoginResponse
import com.example.data.model.loginresponse.UserBodyModel
import retrofit2.Response
import javax.inject.Inject

interface ContentRepository{
    suspend fun onLogin(userBody: UserBodyModel): Response<LoginResponse>
}

class ContentRepositoryImpl@Inject constructor(
    private val contentService: ContentService
): ContentRepository {
    override suspend fun onLogin(userBody: UserBodyModel): Response<LoginResponse> {
        return contentService.getMain(userBody = userBody)
    }

}