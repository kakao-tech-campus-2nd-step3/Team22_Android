package com.team22.soundary.feature.signup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.team22.soundary.databinding.ActivitySignup2Binding

class ActivitySignup2 : AppCompatActivity() {

    private lateinit var binding: ActivitySignup2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignup2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // 가입하기 버튼 클릭 시 메인으로 이동
        binding.signupButtonSubmit.setOnClickListener {
            // 가입 완료 처리 로직 작성
            finish()
        }
    }
}