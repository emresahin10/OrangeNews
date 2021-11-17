package com.denbase.orangenews.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.denbase.orangenews.R
import com.denbase.orangenews.databinding.FragmentCategoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : Fragment(R.layout.fragment_category) {
    private lateinit var binding: FragmentCategoryBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCategoryBinding.bind(view)
    }

}