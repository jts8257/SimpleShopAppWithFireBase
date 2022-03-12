package com.learprogramming.shopapp.commons

import android.app.Application
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

object LoginSession {

    private var pref: SharedPreferences? = null

    fun init(context: Application) {
        val masterKeyAlias = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        pref = EncryptedSharedPreferences.create(
            context,
            "shopapp.login_session",
            masterKeyAlias,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)
    }

    private enum class Key {
        ACCESS_TOKEN, REFRESH_TOKEN, UID, USERNAME;

        fun getInt(): Int? = if (pref!!.contains(name)) pref!!.getInt(name, 0) else null
        fun getString(): String? = if (pref!!.contains(name)) pref!!.getString(name, "") else null

        fun setString(value: String?)  {
            value?.let {
                val editor = pref!!.edit()
                editor.putString(name, value)
                editor.commit()
            }
        }

        fun setInt(value: Int?) {
            value?.let {
                val editor = pref!!.edit()
                editor.putInt(name, value)
                editor.commit()
            }
        }

        fun exists(): Boolean = pref!!.contains(name)
        fun remove() { pref!!.edit().remove(name).commit() }
    }
}