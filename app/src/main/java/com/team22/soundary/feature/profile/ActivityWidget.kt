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

class ActivityWidget : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.widget_main)

        // XML 요소 찾기
        val widgetImageView: ImageView = findViewById(R.id.widget_imageview)
        val widgetTextView: TextView = findViewById(R.id.widget_textview)
        val notificationDot: View = findViewById(R.id.widget_notification_dot)

        // 원형 이미지 처리
        val originalBitmap = BitmapFactory.decodeResource(resources, R.drawable.widget_image)
        val circularBitmapWithHole = createCircularImageWithHole(originalBitmap, 30f)
        widgetImageView.setImageBitmap(circularBitmapWithHole)

        // 닉네임 첫 글자 설정
        val nickname = "쿠키즈" // 예시 닉네임
        widgetTextView.text = nickname.first().toString()

        // 알림이 있을 경우 빨간 점 표시
        val hasNotification = true // 알림이 있다고 가정함
        notificationDot.visibility = if (hasNotification) View.VISIBLE else View.GONE
    }

    // 원형 -> 구멍
    private fun createCircularImageWithHole(sourceBitmap: Bitmap, holeRadius: Float): Bitmap {
        val size = Math.min(sourceBitmap.width, sourceBitmap.height)
        val output = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(output)

        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        val path = Path()
        path.addCircle(size / 2f, size / 2f, size / 2f, Path.Direction.CCW)
        path.addCircle(size / 2f, size / 2f, holeRadius, Path.Direction.CW)
        canvas.clipPath(path)

        val rect = RectF(0f, 0f, size.toFloat(), size.toFloat())
        canvas.drawBitmap(sourceBitmap, null, rect, paint)

        return output
    }
}
