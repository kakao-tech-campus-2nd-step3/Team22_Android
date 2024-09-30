package com.team22.soundary.feature.main.profile

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.team22.soundary.R
import com.team22.soundary.databinding.ActivityMypageBinding
import com.team22.soundary.feature.extensions.checkAndRequestPermissions

class ProfileActivity : AppCompatActivity() {

    companion object{
        private val PERMISSION_REQUEST_CODE = 100
    }
    private lateinit var binding: ActivityMypageBinding

    //registerForActivityResult 쓰기
    private val galleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK && result.data != null) {
            val selectedImage: Uri? = result.data?.data
            binding.profileImageview.setImageURI(selectedImage)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //viewBinding 초기화
        binding = ActivityMypageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initProfileImageView()
    }

    // 초기화 메소드 분리 1
    private fun initProfileImageView() {
        binding.profileImageview.setOnClickListener {
            val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arrayOf(Manifest.permission.READ_MEDIA_IMAGES)
            } else {
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
            }

            if (checkAndRequestPermissions(permissions, PERMISSION_REQUEST_CODE)) {
                openGallery()
            }
        }
    }

    // 갤러리 접근하기
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryLauncher.launch(intent) //registerForActivityResult 사용하기
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openGallery()
                } else {
                    //만약 권한 거부 시 어떻게 할 것인가 - 추후 작성
                }
            }
        }
    }
}