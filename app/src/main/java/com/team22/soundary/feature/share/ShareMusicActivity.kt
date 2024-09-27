package com.team22.soundary.feature.share;

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.team22.soundary.R

class ShareMusicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share_music)

        // spinner
        val spinner = findViewById<Spinner>(R.id.share_sort_spinner)
        spinner.adapter = ArrayAdapter(
            this,
            R.layout.share_spinner_item,
            resources.getStringArray(R.array.share_sort_array)
        )
    }
}