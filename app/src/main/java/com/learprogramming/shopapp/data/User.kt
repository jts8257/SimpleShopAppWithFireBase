package com.learprogramming.shopapp.data

data class User(
    val uid: String = "",
    val nickname: String = "",
    val email: String = "",
    val mobile: Long = 0,
    val gender: String = "",
    val profileCompleted: Int = 0
)
