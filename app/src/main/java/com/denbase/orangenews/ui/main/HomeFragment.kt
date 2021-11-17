package com.denbase.orangenews.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.denbase.orangenews.R
import com.denbase.orangenews.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
    }

}