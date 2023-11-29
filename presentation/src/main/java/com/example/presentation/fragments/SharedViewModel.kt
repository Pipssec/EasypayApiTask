package com.example.presentation.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blogstour.app.util.AppException
import com.blogstour.app.util.Lce
import com.example.data.model.loginresponse.LoginResponse
import com.example.data.model.loginresponse.UserBodyModel
import com.example.data.model.paymentsresponse.PaymentsResponse
import com.example.data.repository.ContentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SharedViewModel@Inject constructor(
    private val contentRepository: ContentRepository
): ViewModel() {

    var token: String = ""

    private val _stateLogin = MutableStateFlow<Lce<LoginResponse>>(Lce.Loading)
    val loginState = _stateLogin.asStateFlow()

    private val _statePayments = MutableStateFlow<Lce<PaymentsResponse>>(Lce.Loading)
    val paymentsState = _stateLogin.asStateFlow()

    private val coroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            viewModelScope.launch {
                handleError(throwable)
            }
        }
    private suspend fun handleError(error: Throwable) {
        _stateLogin.emit(Lce.Error(AppException.NetworkException(error.toString())))
    }

    suspend fun checkUser(login: String, password: String){
        viewModelScope.launch(coroutineExceptionHandler + Dispatchers.IO) {
            _stateLogin.value = Lce.Loading

            val result = contentRepository.onLogin(UserBodyModel(login, password))

            val body = result.body()
            if (result.isSuccessful && body != null){
                _stateLogin.value = Lce.Content(body)
            } else {
                _stateLogin.value = Lce.Error(
                    AppException.NetworkException(result.errorBody().toString())
                )
            }
        }
    }

    suspend fun getPayments(){
        viewModelScope.launch(coroutineExceptionHandler + Dispatchers.IO) {
            _statePayments.value = Lce.Loading

            val result = contentRepository.getPayments(token)

            val body = result.body()
            if (result.isSuccessful && body != null){
                _statePayments.value = Lce.Content(body)
            } else {
                _statePayments.value = Lce.Error(
                    AppException.NetworkException(result.errorBody().toString())
                )
            }
        }
    }

}