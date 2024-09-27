package com.team22.soundary.feature.share;

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.team22.soundary.R
import com.team22.soundary.feature.share.data.FriendItemEntity

class ShareFriendActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share_friend)

        // back Button
        val backButton = findViewById<ImageView>(R.id.share_friend_back_button)

        backButton.setOnClickListener {
            finish()
        }

        // musicInfo
        val musicTextView = findViewById<TextView>(R.id.share_music_textview)
        val singerTextView = findViewById<TextView>(R.id.share_singer_textview)

        val music: String? = intent.extras?.getString("music")
        val singer: String? = intent.extras?.getString("singer")

        musicTextView.text = music
        singerTextView.text = singer

        // recyclerView 임시데이터 생성
        val friendList = mutableListOf<FriendItemEntity>()
        for (i in 0..8) {
            friendList.add(FriendItemEntity("이름"))
        }

        // recyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.share_friend_recyclerview)
        recyclerView.adapter = FriendListAdapter(friendList, LayoutInflater.from(this))
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // bottom Sheet
        val addFriendButton = findViewById<ImageView>(R.id.share_add_friend)

        addFriendButton.setOnClickListener {
            val modal = ShareBottomSheet()
            modal.show(supportFragmentManager, ShareBottomSheet.TAG)
        }
    }
}