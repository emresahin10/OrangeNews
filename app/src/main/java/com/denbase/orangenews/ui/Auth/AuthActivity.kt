package com.denbase.orangenews.ui.Auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.denbase.orangenews.R
import com.denbase.orangenews.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

    }
}