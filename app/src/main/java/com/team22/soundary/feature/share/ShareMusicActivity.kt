package com.team22.soundary.feature.share

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.team22.soundary.R
import com.team22.soundary.databinding.ActivityShareMusicBinding
import com.team22.soundary.feature.share.data.MusicItemEntity

class ShareMusicActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShareMusicBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShareMusicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSpinner()
        setRecyclerView()
    }

    private fun setSpinner() {
        binding.shareSortSpinner.adapter = ArrayAdapter(
            this,
            R.layout.share_spinner_item,
            resources.getStringArray(R.array.share_sort_array)
        )
    }

    private fun setRecyclerView() {
        // recyclerView 임시 데이터 생성
        val musicList = mutableListOf<MusicItemEntity>()
        for (i in 1..20) {
            musicList.add(MusicItemEntity(i, "노래" + i, "가수", "100명이 공유함"))
        }

        val musicListAdapter = MusicListAdapter(musicList, object : MusicItemClickListener {
            override fun onClick(v: View, selectItem: MusicItemEntity) {
                val intent = Intent(this@ShareMusicActivity, ShareFriendActivity::class.java)
                intent.putExtra(ShareFriendActivity.KEY_MUSIC, selectItem.music)
                intent.putExtra(ShareFriendActivity.KEY_SINGER, selectItem.singer)
                startActivity(intent)
            }
        })
        binding.shareMusicRecyclerview.adapter = musicListAdapter
        binding.shareMusicRecyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}