package com.team22.soundary.feature.share;

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.team22.soundary.R
import com.team22.soundary.feature.share.data.MusicItemEntity

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

        // recyclerView 임시데이터 생성
        val musicList = mutableListOf<MusicItemEntity>()
        for (i in 1..20) {
            musicList.add(MusicItemEntity(i, "노래이름"+i, "가수", "100명이 공유함"))
        }

        // recyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.share_music_recyclerview)
        val musicListAdapter = MusicListAdapter(musicList, object : ItemClickListener {
            override fun onClick(v: View, selectItem: MusicItemEntity) {
                val intent = Intent(this@ShareMusicActivity, ShareFriendActivity::class.java)
                intent.putExtra(ShareFriendActivity.KEY_MUSIC, selectItem.music)
                intent.putExtra(ShareFriendActivity.KEY_SINGER, selectItem.singer)
                startActivity(intent)
            }
        })
        recyclerView.adapter = musicListAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}