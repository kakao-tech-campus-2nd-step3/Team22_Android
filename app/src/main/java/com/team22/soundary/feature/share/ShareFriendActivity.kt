package com.team22.soundary.feature.share

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.team22.soundary.databinding.ActivityShareFriendBinding
import com.team22.soundary.feature.share.data.FriendItemEntity

class ShareFriendActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShareFriendBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShareFriendBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setBackButton()
        setMusicInfoText()
        setRecyclerView()
        setAddFriendButton()
    }

    private fun setBackButton() {
        binding.shareFriendBackButton.setOnClickListener {
            finish()
        }
    }

    private fun setMusicInfoText() {
        val music: String? = intent.extras?.getString(KEY_MUSIC)
        val singer: String? = intent.extras?.getString(KEY_SINGER)
        binding.shareMusicTextview.text = music
        binding.shareSingerTextview.text = singer
    }

    private fun setRecyclerView() {
        // recyclerView 임시 데이터 생성
        val friendList = mutableListOf<FriendItemEntity>()
        for (i in 0..4) {
            friendList.add(FriendItemEntity(""+i, "쿠키즈", null, false))
            friendList.add(FriendItemEntity(""+i, "쿠키즈", "imageSrc", false))
        }

        binding.shareFriendRecyclerview.adapter = FriendListAdapter(friendList)
        binding.shareFriendRecyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setAddFriendButton() {
        binding.shareAddFriend.setOnClickListener {
            val modal = ShareBottomSheet()
            modal.show(supportFragmentManager, ShareBottomSheet.TAG)
        }
    }

    companion object {
        const val KEY_MUSIC = "music"
        const val KEY_SINGER = "singer"
    }
}