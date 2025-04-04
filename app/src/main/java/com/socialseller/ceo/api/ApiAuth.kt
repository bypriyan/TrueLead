package com.socialseller.ceo.api

import com.socialseller.ceo.apiResponce.loginResponce.LoginResponce
import com.socialseller.ceo.apiResponce.verifyOtpResponce.VerifyOTPResponce
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiAuth {
    @FormUrlEncoded
    @POST("login")
    suspend fun loginUser(
        @Field("mobile") phone: String
    ): Response<LoginResponce>

    @FormUrlEncoded
    @POST("verify_otp")
    suspend fun verifyOTP(
        @Field("otp") otp: String,
        @Field("mobile") phone: String
    ): Response<VerifyOTPResponce>

}