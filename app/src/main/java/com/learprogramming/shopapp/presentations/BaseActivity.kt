package com.learprogramming.shopapp.presentations

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.learprogramming.shopapp.R
import com.learprogramming.shopapp.presentations.dialog.ProgressDialog


/**
 * A base activity class is used to define the functions and members which we will use in all the activities.
 * It inherits the AppCompatActivity class so in other activity class we will replace the AppCompatActivity with BaseActivity.
 */
open class BaseActivity : AppCompatActivity() {
    lateinit var progressDialog: ProgressDialog

    // TODO Step 4: Create a global instance for progress dialog.
    // START
    /**
     * This is a progress dialog instance which we will initialize later on.
     */
    // END

    /**
     * A function to show the success and error messages in snack bar component.
     */
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

    // TODO Step 5: Create a function to load and show the progress dialog.
    // START
    /**
     * This function is used to show the progress dialog with the title and message to user.
     */
    fun showProgressDialog(text: String) {
        progressDialog =  ProgressDialog(text)
        progressDialog.isCancelable = false
        progressDialog.show(supportFragmentManager, "ProgressDialog")
    }
    // END

    // TODO Step 6: Create a function to hide progress dialog.
    // START
    /**
     * This function is used to dismiss the progress dialog if it is visible to user.
     */
    fun hideProgressDialog() {
        progressDialog.dismiss()
    }
    // END
}

