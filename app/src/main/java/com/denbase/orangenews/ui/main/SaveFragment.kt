package com.denbase.orangenews.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.denbase.orangenews.R
import com.denbase.orangenews.databinding.FragmentSaveBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SaveFragment : Fragment(R.layout.fragment_save) {
    private lateinit var binding: FragmentSaveBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSaveBinding.bind(view)
    }

}