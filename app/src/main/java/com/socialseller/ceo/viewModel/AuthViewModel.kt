package com.socialseller.ceo.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bypriyan.bustrackingsystem.utility.DataStoreManager
import com.socialseller.ceo.apiResponce.loginResponce.LoginResponce
import com.socialseller.ceo.apiResponce.verifyOtpResponce.VerifyOTPResponce
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

    private val _verifyOTP = MutableStateFlow<ApiResponse<VerifyOTPResponce>>(ApiResponse.Loading())
    val verifyOTP: StateFlow<ApiResponse<VerifyOTPResponce>> = _verifyOTP

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

    fun verifyOTP(phone: String, otp: String) {
        viewModelScope.launch {
            _verifyOTP.value = ApiResponse.Loading() // Set loading state
            try {
                val response = authRepository.verifyOTP(otp = otp, phone = phone)
                saveUserData(response)
                _verifyOTP.value = response
            } catch (e: Exception) {
                _verifyOTP.value = ApiResponse.Error("Unexpected error: ${e.message}")
            }
        }
    }

    private suspend fun saveUserData(response: ApiResponse<VerifyOTPResponce>) {
        if (response is ApiResponse.Success) {
            dataStoreManager.saveLoginData(
                token = response.data?.let { it.data.bearer_token }?:"",
                user = response.data?.data!!.user,
                permissions = response.data.data.user.permissions,
                orgId = response.data.data.user.organization_id,
                isModuleSelected = response.data.data.is_module_selected,
                namingConvention = response.data.data.naming_convention_employee
            )
        }
    }

}