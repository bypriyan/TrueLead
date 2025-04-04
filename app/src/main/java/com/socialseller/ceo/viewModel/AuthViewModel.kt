package com.socialseller.ceo.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bypriyan.bustrackingsystem.utility.DataStoreManager
import com.socialseller.ceo.apiResponce.loginResponce.LoginResponce
import com.socialseller.ceo.repository.AuthRepository
import com.socialseller.clothcrew.apiResponce.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    private val _loginUser = MutableStateFlow<ApiResponse<LoginResponce>>(ApiResponse.Loading())
    val loginUser: StateFlow<ApiResponse<LoginResponce>> = _loginUser

     fun loginUser(phone: String) {
        viewModelScope.launch {
            _loginUser.value = ApiResponse.Loading() // Set loading state
            try {
                val response = authRepository.loginUser(phone)
                _loginUser.value = response
            } catch (e: Exception) {
                _loginUser.value = ApiResponse.Error("Unexpected error: ${e.message}")
            }
        }
    }

}