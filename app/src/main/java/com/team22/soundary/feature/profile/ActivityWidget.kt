package com.team22.soundary.feature.profile

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.team22.soundary.R
import com.team22.soundary.databinding.WidgetMainBinding

class ActivityWidget : AppCompatActivity() {

    private lateinit var binding: WidgetMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.widget_main)

        // ViewBinding 초기화
        binding = WidgetMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // binding으로 뷰 접근
        val widgetImageView = binding.widgetImageview
        val widgetTextView = binding.widgetTextview
        val notificationDot = binding.widgetNotificationDot


        // 닉네임 첫 글자 설정
        val nickname = "쿠키즈" // 예시 닉네임
        widgetTextView.text = nickname.first().toString()

        // 알림이 있을 경우 빨간 점 표시
        val hasNotification = true // 알림이 있다고 가정함
        notificationDot.visibility = if (hasNotification) View.VISIBLE else View.GONE
    }
}
