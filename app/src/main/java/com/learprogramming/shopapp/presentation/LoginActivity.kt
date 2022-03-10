package com.learprogramming.shopapp.presentation

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.learprogramming.shopapp.R
import com.learprogramming.shopapp.databinding.ActivityLoginBinding

/**
 * Login Screen of the application.
 */
@Suppress("DEPRECATION")
class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    /**
     * This function is auto created by Android when the Activity Class is created.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        //This call the parent constructor
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        // This is used to align the xml view to this class
        setContentView(binding.root)

        // This is used to hide the status bar and make the login screen as a full screen activity.
        // It is deprecated in the API level 30. I will update you with the alternate solution soon.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        binding.tvRegister.setOnClickListener {
            // Launch the register screen when the user clicks on the text.
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}