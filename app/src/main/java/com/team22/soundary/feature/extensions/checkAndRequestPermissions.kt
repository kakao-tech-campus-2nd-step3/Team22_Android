package com.team22.soundary.feature.extensions

import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity

// Activity에 대한 확장 함수 정의
fun AppCompatActivity.checkAndRequestPermissions(permissions: Array<String>, requestCode: Int): Boolean {
    val permissionsNeeded = mutableListOf<String>()

    // 요청할 권한을 순회하며 필요한 권한만 추가
    permissions.forEach { permission ->
        val permissionStatus = ContextCompat.checkSelfPermission(this, permission)
        if (permissionStatus != PackageManager.PERMISSION_GRANTED) {
            permissionsNeeded.add(permission)
        }
    }

    // 권한 요청 필요 시 처리
    return if (permissionsNeeded.isNotEmpty()) {
        ActivityCompat.requestPermissions(this, permissionsNeeded.toTypedArray(), requestCode)
        false
    } else {
        true
    }
}