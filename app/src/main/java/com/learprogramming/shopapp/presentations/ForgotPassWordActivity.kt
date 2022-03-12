package com.learprogramming.shopapp.presentations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.learprogramming.shopapp.databinding.ActivityForgotPassWordBinding

class ForgotPassWordActivity : AppCompatActivity() {

    lateinit var binding: ActivityForgotPassWordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityForgotPassWordBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}