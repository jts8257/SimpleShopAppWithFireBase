package com.learprogramming.shopapp.repositories.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.learprogramming.shopapp.commons.Constants
import com.learprogramming.shopapp.commons.LoginSession
import com.learprogramming.shopapp.commons.basis
import com.learprogramming.shopapp.commons.userBasis
import com.learprogramming.shopapp.data.User

class FireStoreRepository {

    private val _firestore = FirebaseFirestore.getInstance()

    fun registerUser(userInfo: User, onSuccess: basis, onFail: basis) {
        _firestore.collection(Constants.USERS)
            // users filed 의 Document ID
            .document(userInfo.uid)
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onFail()
            }
    }

    fun getCurrentUID(): String {
        val currentUser = FirebaseAuth.getInstance().currentUser

        var currentUid  = ""
        currentUser?.let {
            currentUid = it.uid
        }
        return currentUid
    }

    fun getUserDetails(onSuccess: userBasis, onFail: basis) {
        _firestore.collection(Constants.USERS)
            .document(getCurrentUID())
            .get()
            .addOnSuccessListener { document ->
                val user = document.toObject(User::class.java)!!
                LoginSession.nickname = user.nickname
                LoginSession.profileImage = user.image
                LoginSession.email = user.email
                LoginSession.gender = user.gender
                LoginSession.mobile = user.mobile
                onSuccess(user)
            }
            .addOnFailureListener { e ->
            e.printStackTrace()
                onFail()
            }
    }

    fun updateUser(userInfo: User, onSuccess: basis, onFail: basis) {
        _firestore.collection(Constants.USERS)
            .document(getCurrentUID())
            .update(userInfo.toHashMap())
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onFail()
            }
    }
}