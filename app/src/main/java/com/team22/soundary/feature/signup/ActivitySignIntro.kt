package com.team22.soundary.feature.signup

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.team22.soundary.databinding.ActivitySignIntroBinding

class ActivitySignIntro : AppCompatActivity() {

    private lateinit var binding: ActivitySignIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // signup 으로 이동
        binding.startButton.setOnClickListener {
            val intent = Intent(this, ActivitySignup::class.java)
            startActivity(intent)
        }
    }
}