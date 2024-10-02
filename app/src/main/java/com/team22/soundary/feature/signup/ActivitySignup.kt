package com.team22.soundary.feature.signup

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.team22.soundary.databinding.ActivitySignupBinding

class ActivitySignup : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //signup2 이동
        binding.signupButtonContinue.setOnClickListener {
            val intent = Intent(this, ActivitySignup2::class.java)
            startActivity(intent)
        }
    }
}