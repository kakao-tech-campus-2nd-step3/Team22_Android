package com.team22.soundary.feature.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import com.team22.soundary.databinding.ActivitySignupBinding

class ActivitySignup : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private val selectedCategories = mutableListOf<ToggleButton>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //이메일 예시
        setEmailId("user@example.com")

        val categoryButtons = listOf(
            binding.signupTogglebuttonCategoryHiphop,
            binding.signupTogglebuttonCategoryRock,
            binding.signupTogglebuttonCategoryPop,
            binding.signupTogglebuttonCategoryJpop,
            binding.signupTogglebuttonCategoryBallad,
            binding.signupTogglebuttonCategoryDance,
            binding.signupTogglebuttonCategoryRnb
        )

        // 카테고리 선택 처리 (배경색 변화를 제거)
        for (button in categoryButtons) {
            button.setOnClickListener {
                if (button.isChecked) {
                    if (selectedCategories.size < 3) {
                        selectedCategories.add(button)
                    } else {
                        button.isChecked = false
                        Toast.makeText(this, "최대 3개까지 선택 가능합니다.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    selectedCategories.remove(button)
                }
            }
        }

        // 계속 가입하기 버튼 클릭 시 처리
        binding.signupButtonContinue.setOnClickListener {
            val nickname = binding.signupEdittextNickname.text.toString()

            if (nickname.isEmpty()) {
                Toast.makeText(this, "닉네임은 필수 입력칸입니다.", Toast.LENGTH_SHORT).show()
            } else if (selectedCategories.isEmpty()) {
                Toast.makeText(this, "카테고리를 하나 이상 선택해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, ActivitySignup2::class.java)
                startActivity(intent)
            }
        }
    }
    private fun setEmailId(email: String) {
        // 이메일의 앞 부분만 추출하여 설정 (예시로 '@' 앞부분)
        val emailId = email.substringBefore("@")
        binding.signupEdittextEmail.setText(emailId)
        binding.signupEdittextEmail.isEnabled = false // 입력 불가하게 설정
    }
}
