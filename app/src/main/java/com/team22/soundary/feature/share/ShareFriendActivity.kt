package com.team22.soundary.feature.share

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.team22.soundary.databinding.ActivityShareFriendBinding
import com.team22.soundary.feature.share.data.FriendItemEntity
import kotlinx.coroutines.launch

class ShareFriendActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShareFriendBinding

    private lateinit var adapter: FriendListAdapter
    private val viewModel: ShareViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShareFriendBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.init()

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
        adapter = FriendListAdapter()
        binding.shareFriendRecyclerview.adapter = adapter
        binding.shareFriendRecyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        lifecycleScope.launch {
            viewModel.selectList.collect {
                adapter.submitList(it)
            }
        }
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