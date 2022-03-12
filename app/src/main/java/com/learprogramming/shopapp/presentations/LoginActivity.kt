package com.learprogramming.shopapp.presentations

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.WindowInsets
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import com.learprogramming.shopapp.commons.LoginSession
import com.learprogramming.shopapp.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch

class LoginActivity: BaseActivity(), View.OnClickListener {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        checkLoginSession()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        }

//        View.OnClickListener 를 구현했기 때문에 이렇게 할 수 있음.
        binding.tvForgotPassword.setOnClickListener(this)
        binding.tvRegister.setOnClickListener(this)
        binding.btnLogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v) {
                binding.tvForgotPassword -> {
                    startActivity(Intent(this@LoginActivity, ForgotPassWordActivity::class.java))
                }
                binding.tvRegister -> {
                    val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                    startActivity(intent)
                }
                binding.btnLogin -> {
                    val email: String = binding.etEmail.text.toString().trim { it <= ' ' }
                    val password: String = binding.etEmail.text.toString().trim { it <= ' ' }
                    if(isValidEmailAndPassWord(email, password)) {
                        showProgressDialog("로그인 중...")

                        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    lifecycleScope.launch {
                                        LoginSession.email = email
                                        LoginSession.passwd = password
                                    }
                                    hideProgressDialog()
                                    loginSuccess()
                                } else {
                                    showErrorSnackBar(task.exception!!.message.toString(),
                                        true)
                                }
                            }
                    }
                }

            }
        }
    }

    private fun checkLoginSession() {
        lifecycleScope.launch {
            val email = LoginSession.email
            val passwd = LoginSession.passwd
            if (email != null && passwd != null) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, passwd)
                    .addOnCompleteListener { task ->
                        if(task.isSuccessful) {
                            loginSuccess()
                        }
                    }
            }
        }
    }

    private fun loginSuccess() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun isValidEmailAndPassWord(email: String, passwd: String):Boolean {
        return when {
            TextUtils.isEmpty(email.trim {it <= ' '}) -> {
                showErrorSnackBar("이메일 입력을 확인해주세요", true)
                false
            }
            TextUtils.isEmpty(passwd.trim {it <= ' '}) -> {
                showErrorSnackBar("비밀번호 입력을 확인해주세요", true)
                false
            }
            else ->  {
                true
            }
        }
    }
}