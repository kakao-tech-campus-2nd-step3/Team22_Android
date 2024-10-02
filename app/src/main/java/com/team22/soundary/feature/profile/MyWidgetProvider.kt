package com.team22.soundary.feature.profile

import android.annotation.SuppressLint
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.view.View
import android.widget.RemoteViews
import com.team22.soundary.R

class MyWidgetProvider : AppWidgetProvider() {

    @SuppressLint("RemoteViewLayout")
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // 각 위젯을 업데이트합니다.
        for (appWidgetId in appWidgetIds) {
            val views = RemoteViews(context.packageName, R.layout.widget_main)

            // 닉네임 설정
            val nickname = "쿠키즈"
            views.setTextViewText(R.id.widget_textview, nickname.first().toString())

            // 이미지 설정 추가
            views.setImageViewResource(R.id.widget_imageview, R.drawable.widget_image)

            // 알림 여부에 따른 점 표시
            val hasNotification = true // 알림이 있다고 가정
            if (hasNotification) {
                views.setViewVisibility(R.id.widget_notification_dot, View.VISIBLE)
            } else {
                views.setViewVisibility(R.id.widget_notification_dot, View.GONE)
            }

            // 위젯 업데이트
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}
