package com.learprogramming.shopapp.presentations

import android.os.Bundle
import android.text.TextUtils
import com.google.firebase.auth.FirebaseAuth
import com.learprogramming.shopapp.R
import com.learprogramming.shopapp.databinding.ActivityForgotPassWordBinding

class ForgotPassWordActivity : BaseActivity() {

    lateinit var binding: ActivityForgotPassWordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPassWordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionBar()

        binding.btnSubmit.setOnClickListener {
            val email = binding.etEmail.text.toString().trim { it <= ' '}
            if (validateEmail(email)) {
                showProgressDialog("기다려 주세요...")
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        hideProgressDialog()
                        if (task.isSuccessful) {
                            showToastMessage("이메일을 전송했습니다.")
                            finish()
                        } else {
                            showToastMessage(task.exception!!.message.toString())
                        }
                    }
            }
        }
    }


    private fun setupActionBar() {
    setSupportActionBar(binding.toolbarForgotPasswordActivity)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24dp)
    binding.toolbarForgotPasswordActivity.setNavigationOnClickListener { onBackPressed() }
    }

    private fun validateEmail(email: String): Boolean {
        if (email.isEmpty()) {
            showErrorSnackBar("이메일을 확인해주세요", true)
            return false
        }
        return true
    }
}