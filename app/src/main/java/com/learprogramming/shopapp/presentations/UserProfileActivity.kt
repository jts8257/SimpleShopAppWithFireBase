package com.learprogramming.shopapp.presentations

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.learprogramming.shopapp.R
import com.learprogramming.shopapp.commons.PermissionCodes.CAMERA_PERMISSION_CODE
import com.learprogramming.shopapp.commons.PermissionCodes.READ_EXTERNAL_FILE_PERMISSION_CODE
import com.learprogramming.shopapp.databinding.ActivityUserProfileBinding
import com.learprogramming.shopapp.presentations.dialog.ProfileImageEditActions
import com.learprogramming.shopapp.presentations.dialog.ProfileImageEditOptionDialog
import com.learprogramming.shopapp.viewmodels.UserProfileViewModel
import kotlinx.coroutines.launch
import java.io.IOException

class UserProfileActivity: BaseActivity(), ProfileImageEditActions {

    private lateinit var binding: ActivityUserProfileBinding
    private lateinit var profileImageEdit: ProfileImageEditOptionDialog
    private lateinit var getAImageResult: ActivityResultLauncher<Intent>
    private lateinit var takeAImageResult: ActivityResultLauncher<Intent>

    private val viewModel: UserProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupActionBar()
        bindData()
        binding.etEmail.isEnabled = false
        setUpResultLauncher()

        binding.flUserImage.setOnClickListener {
            profileImageEdit = ProfileImageEditOptionDialog(this@UserProfileActivity)
            profileImageEdit.show(supportFragmentManager, "dialog")
        }

        binding.btnSubmit.setOnClickListener {
            showProgressDialog("?????????...")
            // TODO ???????????? ????????? ?????? ?????? ????????? ?????? string ??? ?????? ?????? ??????????????? ?????? ???????????????
        }
    }

    private fun bindData() {
        lifecycleScope.launch {

            binding.etNickname.setText(viewModel.getUserNickname())
            binding.etEmail.setText(viewModel.getUserEmail())

            val mobile = viewModel.getUserMobile()
            if (mobile != 0L) {
                binding.etMobileNumber.setText(mobile.toString())
            }

            val profile = viewModel.getUserImage()
            if (profile.isNotEmpty()) {
                // TODO Inject ?????? Glide ??? ?????? ??????
                Glide.with(this@UserProfileActivity)
                    .load(profile)
                    .into(binding.ivUserPhoto)
            }

                when (viewModel.getUserGender()) {
                    "male" -> {
                        binding.rbMale.isSelected = true
                        binding.rbFemale.isSelected = false
                    }
                    "female" -> {
                        binding.rbMale.isSelected = false
                        binding.rbFemale.isSelected = true
                    }
                }
        }
    }

    private fun setupActionBar() {
        setSupportActionBar(binding.toolbarUserProfileActivity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24dp)
        binding.toolbarUserProfileActivity.setNavigationOnClickListener { onBackPressed() }
    }

    override fun onCameraClicked() {
        if (ContextCompat.checkSelfPermission(this@UserProfileActivity,
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            // ????????? ???????????? ??????
        } else {
            this.requestPermissions(
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_CODE)
        }
    }

    override fun onAlbumClicked() {
        if (ContextCompat.checkSelfPermission(this@UserProfileActivity,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            // ?????? ??????
        } else {
            this@UserProfileActivity.requestPermissions(
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                READ_EXTERNAL_FILE_PERMISSION_CODE)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == READ_EXTERNAL_FILE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                profileImageEdit.dismiss()
            } else {

                showErrorSnackBar("????????? ???????????? ????????? ???????????? ???????????????.", true)
            }
        } else if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                profileImageEdit.dismiss()
            } else {
                showErrorSnackBar("????????? ???????????? ???????????? ???????????? ???????????????.", true)
            }
        }
    }

    private fun setUpResultLauncher() {
        getAImageResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { activityResult ->
            if (activityResult.resultCode == RESULT_OK) {
                try {
                    activityResult.data?.data?.let { uri ->
//                     TODO   Inject ?????? Glide ??? ?????? ??????
                        binding.ivUserPhoto.setImageURI(uri)
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                    showErrorSnackBar("????????? ?????? ??? ????????? ??????????????????.", true)
                }
            } else {
                showErrorSnackBar("???????????? ???????????? ???????????????.", true)
            }
        }
    }
}