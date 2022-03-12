package com.learprogramming.shopapp.commons

import com.learprogramming.shopapp.data.User

typealias basis = () -> Unit
typealias userBasis = (User) -> Unit

object Constants {
    const val USERS: String = "users"
}