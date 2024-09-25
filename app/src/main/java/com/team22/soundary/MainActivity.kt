package com.team22.soundary

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private val PERMISSION_REQUEST_CODE = 100
    private lateinit var profileImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)

        profileImageView = findViewById(R.id.profile_imageview)

        profileImageView.setOnClickListener {
            if (checkAndRequestPermissions()) {
                openGallery()
            }
        }
    }

    // 권한 체크 및 요청
    private fun checkAndRequestPermissions(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Android 13 이상에서는 READ_MEDIA_IMAGES 권한을 사용
            val readPermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES)
            } else {
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
            }

            val cameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)

            val listPermissionsNeeded = ArrayList<String>()
            if (readPermission != PackageManager.PERMISSION_GRANTED) {
                // Android 13 이상은 READ_MEDIA_IMAGES 권한 요청, 그 외는 READ_EXTERNAL_STORAGE
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    listPermissionsNeeded.add(Manifest.permission.READ_MEDIA_IMAGES)
                } else {
                    listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE)
                }
            }
            if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.CAMERA)
            }
            if (listPermissionsNeeded.isNotEmpty()) {
                ActivityCompat.requestPermissions(this, listPermissionsNeeded.toTypedArray(), PERMISSION_REQUEST_CODE)
                return false
            }
        }
        return true
    }

    // 갤러리 열기
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PERMISSION_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PERMISSION_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImage: Uri? = data.data
            profileImageView.setImageURI(selectedImage)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openGallery()
                } else {
                    // 권한이 거부된 경우
                }
            }
        }
    }
}
