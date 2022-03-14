package com.learprogramming.shopapp.viewmodels

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.learprogramming.shopapp.commons.LoginSession
import com.learprogramming.shopapp.data.User

class UserProfileViewModel: ViewModel() {

    private var _userInfo: User = LoginSession.createUserInfo()

    fun getUserEmail(): String = _userInfo.email
    fun getUserNickname(): String = _userInfo.nickname
    fun getUserImage(): String = _userInfo.image
    fun getUserMobile(): Long = _userInfo.mobile
    fun getUserGender(): String = _userInfo.gender

    fun updateProfileInformation(
        nickname: String = _userInfo.nickname,
        gender: String = _userInfo.gender,
        mobile: String = _userInfo.mobile.toString(),
        image: Uri) {
    }
}