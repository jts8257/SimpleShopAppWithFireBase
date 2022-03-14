package com.learprogramming.shopapp.presentations.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.learprogramming.shopapp.databinding.DialogProfileImageEditBinding

class ProfileImageEditOptionDialog(
    private val actions: ProfileImageEditActions
    ): BottomSheetDialogFragment() {

    private var _binding: DialogProfileImageEditBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogProfileImageEditBinding.inflate(layoutInflater, container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.takeAPhoto.setOnClickListener {
            actions.onCameraClicked()
        }
        binding.pickAPhoto.setOnClickListener {
            actions.onAlbumClicked()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}