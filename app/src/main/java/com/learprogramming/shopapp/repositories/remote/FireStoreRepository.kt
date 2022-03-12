package com.learprogramming.shopapp.repositories.remote

import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.learprogramming.shopapp.commons.Constants
import com.learprogramming.shopapp.commons.basis
import com.learprogramming.shopapp.commons.userBasis
import com.learprogramming.shopapp.data.User
import com.learprogramming.shopapp.presentations.LoginActivity
import com.learprogramming.shopapp.presentations.RegisterActivity

class FireStoreRepository {

    private val mFirestore = FirebaseFirestore.getInstance()

    fun registerUser(userInfo: User, onSuccess: basis, onFail: basis) {
        mFirestore.collection(Constants.USERS)
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
        mFirestore.collection(Constants.USERS)
            .document(getCurrentUID())
            .get()
            .addOnSuccessListener { document ->
                val user = document.toObject(User::class.java)!!
                onSuccess(user)

            }
            .addOnFailureListener { e ->
            e.printStackTrace()
                onFail()
            }
    }
}