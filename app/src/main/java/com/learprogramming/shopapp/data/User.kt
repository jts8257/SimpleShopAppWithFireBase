package com.learprogramming.shopapp.data

data class User (
    val uid: String = "",
    var nickname: String = "",
    var image: String = "",
    val email: String = "",
    var mobile: Long = 0,
    var gender: String = ""
) {
    fun toHashMap(): HashMap<String, Any> {
        val map = HashMap<String, Any>()
        map[gender] = gender
        map[nickname] = nickname
        map[mobile.toString()] = mobile
        map[image] = image
        return map
    }
}