package com.learprogramming.shopapp.commons.utils

import android.content.Intent
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher

class ExternalActivityUtil {

    fun showImageChooser(resultLauncher: ActivityResultLauncher<Intent>) {
        val galleryIntent = Intent(Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        resultLauncher.launch(galleryIntent)
    }

    fun activateCamera(resultLauncher: ActivityResultLauncher<Intent>) {

    }
}