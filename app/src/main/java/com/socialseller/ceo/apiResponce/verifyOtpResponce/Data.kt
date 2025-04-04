package com.socialseller.ceo.apiResponce.verifyOtpResponce

data class Data(
    val bearer_token: String,
    val is_module_selected: Int,
    val message: String,
    val naming_convention_employee: String,
    val user: User
)