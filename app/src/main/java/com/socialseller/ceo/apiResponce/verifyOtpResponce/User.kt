package com.socialseller.ceo.apiResponce.verifyOtpResponce

data class User(
    val departments: List<Department>,
    val email: String,
    val id: Int,
    val mobile: String,
    val name: String,
    val organization_id: Int,
    val organization_modules: List<Any>,
    val permissions: List<Permission>,
    val total_distribution_coins: Int,
    val uuid: String,
    val wallet_coins: Int
)