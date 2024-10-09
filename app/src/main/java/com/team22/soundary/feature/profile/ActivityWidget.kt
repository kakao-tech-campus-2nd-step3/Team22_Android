package com.team22.soundary.feature.profile


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
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
        binding.textCircleBackground.text = nickname.first().toString()

        // 이미지 설정
        val originalBitmap =
            BitmapFactory.decodeResource(this.getResources(), R.drawable.widget_image)
        val circularBitmap: Bitmap = ImageUtil().getCircularBitmap(originalBitmap)
        binding.profileFrame.setImageBitmap(circularBitmap)

        // 알림 여부에 따른 점 표시
        val hasNotification = true
        binding.widgetNotificationDot.visibility = if (hasNotification) View.VISIBLE else View.GONE
    }
}

