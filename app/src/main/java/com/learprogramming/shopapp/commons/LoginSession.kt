package com.learprogramming.shopapp.commons

import android.app.Application
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.learprogramming.shopapp.commons.Constants.PREF_NAME
import com.learprogramming.shopapp.data.User

object LoginSession {

    private var pref: SharedPreferences? = null

    fun init(context: Application) {
        val masterKeyAlias = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        pref = EncryptedSharedPreferences.create(
            context,
            PREF_NAME,
            masterKeyAlias,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)
    }

    var nickname: String?
    get() = Key.NICKNAME.getString()
    set(value) = Key.NICKNAME.setString(value)

    var profileImage: String?
    get() = Key.PROFILE_IMAGE.getString()
    set(value) = Key.PROFILE_IMAGE.setString(value)

    var email: String?
    get() = Key.EMAIL.getString()
    set(value) = Key.EMAIL.setString(value)

    var gender: String?
        get() = Key.GENDER.getString()
        set(value) = Key.GENDER.setString(value)

    var mobile: Long?
        get() = Key.MOBILE.getLong()
        set(value) = Key.MOBILE.setLong(value)

    fun createUserInfo(): User {
        return User(
            nickname = nickname?:"",
            image = profileImage?:"",
            email = email?:"",
            mobile = mobile?:0,
            gender = gender?:""
        )
    }

    fun removeLoginSession() {
        if (Key.NICKNAME.exists()) {
            Key.NICKNAME.remove()
        }
        if (Key.PROFILE_IMAGE.exists()) {
            Key.PROFILE_IMAGE.remove()
        }
        if (Key.EMAIL.exists()) {
            Key.EMAIL.remove()
        }
        if (Key.GENDER.exists()) {
            Key.GENDER.remove()
        }
        if (Key.MOBILE.exists()) {
            Key.MOBILE.remove()
        }
    }

    private enum class Key {
       EMAIL, NICKNAME, PROFILE_IMAGE, MOBILE, GENDER;

        fun getString(): String? = if (pref!!.contains(name)) pref!!.getString(name, "") else null
        fun getLong(): Long? = if (pref!!.contains(name)) pref!!.getLong(name, 0) else null

        fun setString(value: String?)  {
            value?.let {
                val editor = pref!!.edit()
                editor.putString(name, value)
                editor.commit()
            }
        }
        fun setLong(value: Long?) {
            value?.let {
                val editor = pref!!.edit()
                editor.putLong(name, value)
                editor.commit()
            }
        }

        fun exists(): Boolean = pref!!.contains(name)
        fun remove() { pref!!.edit().remove(name).apply() }
    }
}