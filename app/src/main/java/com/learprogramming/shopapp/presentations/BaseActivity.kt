package com.learprogramming.shopapp.presentations

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.learprogramming.shopapp.R
import com.learprogramming.shopapp.presentations.dialog.ProgressDialog

open class BaseActivity: AppCompatActivity() {
    lateinit var progressDialog: ProgressDialog

    fun showToastMessage(message: String) {
        Toast.makeText(this@BaseActivity, message, Toast.LENGTH_SHORT)
            .show()
    }

    fun showErrorSnackBar(message: String, errorMessage: Boolean) {
        val snackBar =
            Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
        val snackBarView = snackBar.view

        if (errorMessage) {
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(
                    this@BaseActivity,
                    R.color.colorSnackBarError
                )
            )
        }else{
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(
                    this@BaseActivity,
                    R.color.colorSnackBarSuccess
                )
            )
        }
        snackBar.show()
    }

    fun showProgressDialog(text: String) {
        progressDialog =  ProgressDialog(text)
        progressDialog.isCancelable = false
        progressDialog.show(supportFragmentManager, "ProgressDialog")
    }

    fun hideProgressDialog() {
        progressDialog.dismiss()
    }
}

