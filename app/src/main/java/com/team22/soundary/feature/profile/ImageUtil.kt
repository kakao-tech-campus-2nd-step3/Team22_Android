package com.team22.soundary.feature.profile

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import com.team22.soundary.R


class ImageUtil {

    fun getCircularBitmap(bitmap: Bitmap): Bitmap {
        val output = Bitmap.createBitmap(
            bitmap.getWidth(),
            bitmap.getHeight(), Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(output)
        val color = -0xbdbdbe
        val paint = Paint()
        val rect = Rect(0, 0, bitmap.getWidth(), bitmap.getHeight())
        paint.isAntiAlias = true
        canvas.drawARGB(0, 0, 0, 0)
        paint.setColor(color)
        canvas.drawCircle(
            (bitmap.getWidth() / 2).toFloat(), (bitmap.getHeight() / 2).toFloat(),
            (
                    bitmap.getWidth() / 2).toFloat(), paint
        )
        paint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.SRC_IN))
        canvas.drawBitmap(bitmap, rect, rect, paint)
        return output
    }
}