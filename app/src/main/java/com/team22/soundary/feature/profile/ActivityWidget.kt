package com.team22.soundary.feature.profile

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.team22.soundary.R
import com.team22.soundary.databinding.WidgetMainBinding

class ActivityWidget : AppCompatActivity() {

    private lateinit var binding: WidgetMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding 초기화
        binding = WidgetMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 닉네임 설정
        val nickname = "쿠키즈"
        binding.widgetTextview.text = nickname.first().toString()

        // 이미지 설정
        binding.widgetImageview.setImageResource(R.drawable.widget_image)

        // 알림 여부에 따른 점 표시
        val hasNotification = true
        binding.widgetNotificationDot.visibility = if (hasNotification) View.VISIBLE else View.GONE
    }
}
