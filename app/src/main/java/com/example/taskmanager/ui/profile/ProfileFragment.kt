package com.example.taskmanager.ui.profile


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.taskmanager.R
import com.example.taskmanager.data.local.Pref

import com.example.taskmanager.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding



    private val pref: Pref by lazy {
        Pref(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       binding.editTextProfileName.setText(pref.getName())
        binding.btnSaveName.setOnClickListener {
            pref.saveName(binding.editTextProfileName.text.toString())
        }
        Glide.with(requireContext())
            .load(pref.getAvatar())
            .apply(RequestOptions.circleCropTransform())
            .into(binding.imageViewProfile)

        binding.imageViewProfile.setOnClickListener {
            fileChooserContract.launch("image/*")
        }
    }

    private val fileChooserContract = registerForActivityResult(ActivityResultContracts.GetContent()) { imageUri ->
        if (imageUri != null) {
            Glide.with(requireContext())
                .load(imageUri)
                .apply(RequestOptions.circleCropTransform())
                .into(binding.imageViewProfile)

            pref.saveAvatar(imageUri.toString())
            // imageUri now contains URI to selected image
        }
    }
}
