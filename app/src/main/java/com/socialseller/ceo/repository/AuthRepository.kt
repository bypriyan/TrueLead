package com.socialseller.ceo.repository

import com.socialseller.ceo.api.ApiAuth
import com.socialseller.ceo.apiResponce.loginResponce.LoginResponce
import com.socialseller.ceo.apiResponce.verifyOtpResponce.VerifyOTPResponce
import com.socialseller.clothcrew.apiResponce.ApiResponse
import com.socialseller.clothcrew.utility.ResponceHelper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(private val apiAuth: ApiAuth) {

    suspend fun loginUser(phone: String): ApiResponse<LoginResponce> {
        return ResponceHelper.safeApiCall { apiAuth.loginUser(phone) }
    }

    suspend fun verifyOTP(phone: String, otp: String): ApiResponse<VerifyOTPResponce> {
        return ResponceHelper.safeApiCall { apiAuth.verifyOTP(otp, phone) }
    }

}