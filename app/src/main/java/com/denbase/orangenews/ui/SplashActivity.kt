package com.denbase.orangenews.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.denbase.orangenews.databinding.ActivitySplashBinding
import com.denbase.orangenews.ui.auth.AuthActivity
import com.denbase.orangenews.utils.Constants.Companion.SPLASH_TIME
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val scope = MainScope()
    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        startDelay()


    }
    private fun startDelay(){
        stopDelay()
        job = scope.launch {
            delay(SPLASH_TIME)
            val intent = Intent(this@SplashActivity, AuthActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun stopDelay(){
        job?.cancel()
        job = null
    }
}