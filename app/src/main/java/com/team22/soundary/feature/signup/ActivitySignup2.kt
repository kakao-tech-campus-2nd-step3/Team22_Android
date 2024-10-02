package com.team22.soundary.feature.signup

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.team22.soundary.databinding.ActivitySignup2Binding
import com.team22.soundary.feature.extensions.checkAndRequestPermissions

class ActivitySignup2 : AppCompatActivity() {

    private lateinit var binding: ActivitySignup2Binding
    private val PERMISSION_REQUEST_CODE = 100
    private val GALLERY_REQUEST_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignup2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // 갤러리 접근 권한 요청
        val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arrayOf(Manifest.permission.READ_MEDIA_IMAGES)
        } else {
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        // 권한이 부여되었을 경우 갤러리 열기
        binding.signupImageviewProfileimage.setOnClickListener {
            if (checkAndRequestPermissions(permissions, PERMISSION_REQUEST_CODE)) {
                accessGallery()
            }
        }

        // 가입하기 버튼 클릭 시 메인으로 이동
        binding.signupButtonSubmit.setOnClickListener {
            // 가입 완료 처리 로직 작성
            finish()
        }
    }

    // 권한이 부여된 후 갤러리에 접근하는 함수
    private fun accessGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }

    // 갤러리에서 이미지 선택 후 처리
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            val selectedImageUri: Uri? = data.data
            if (selectedImageUri != null) {
                // 선택된 이미지를 ImageView에 표시
                binding.signupImageviewProfileimage.setImageURI(selectedImageUri)
            } else {
                Toast.makeText(this, "이미지를 불러오지 못했습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // 권한 요청 결과 처리
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSION_REQUEST_CODE) {
            val allGranted = grantResults.all { it == PackageManager.PERMISSION_GRANTED }
            if (allGranted) {
                // 권한이 모두 부여되었을 경우 갤러리 접근 가능
                accessGallery()
            } else {
                // 권한이 거부되었을 때 처리
                Toast.makeText(this, "갤러리 접근 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
