package com.team22.soundary.feature.share;

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
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

        // recyclerView

        // 임시데이터 생성
        val musicList = mutableListOf<MusicItemEntity>()
        for (i in 0..5) {
            musicList.add(MusicItemEntity(i, "노래이름1", "가수", "100명이 공유함"))
            musicList.add(MusicItemEntity(i, "노래이름2", "가수", "100명이 공유함"))
        }

        val recyclerView = findViewById<RecyclerView>(R.id.share_music_recyclerview)
        val mapListAdapter = MusicListAdapter(musicList, LayoutInflater.from(this))
        recyclerView.adapter = mapListAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        mapListAdapter.setItemClickListener(object : ItemClickListener {
            override fun onClick(v: View, musicItem: MusicItemEntity) {
                val intent = Intent(this@ShareMusicActivity, ShareFriendActivity::class.java)
                intent.putExtra("music", musicItem.music)
                intent.putExtra("singer", musicItem.singer)
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
        })
    }
}