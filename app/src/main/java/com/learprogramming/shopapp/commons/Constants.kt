package com.learprogramming.shopapp.commons

import com.learprogramming.shopapp.data.User

typealias basis = () -> Unit
typealias userBasis = (User) -> Unit

object Constants {
    const val USERS: String = "users"
    const val PREF_NAME: String = "shopapp.login_session"

}