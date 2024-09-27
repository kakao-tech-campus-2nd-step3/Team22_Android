package com.team22.soundary.feature.main

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.team22.soundary.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)

        val spinner = findViewById<Spinner>(R.id.sort_spinner)
        spinner.adapter = ArrayAdapter(this,
            R.layout.main_spinner_item,resources.getStringArray(R.array.mock_array))
    }
}