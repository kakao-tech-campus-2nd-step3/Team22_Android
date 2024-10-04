package com.team22.soundary.feature.share

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.team22.soundary.databinding.ActivityShareFriendBinding
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
        setComment()
        setAddFriendButton()
        setSendButton()
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
                binding.shareSendButton.text = viewModel.getButtonText()
            }
        }
    }

    private fun setComment() {
        lifecycleScope.launch {
            viewModel.comment.collect {
                binding.shareCommentEdittext.setText(it)
            }
        }
    }

    private fun setAddFriendButton() {
        binding.shareAddFriend.setOnClickListener {
            viewModel.setComment(binding.shareCommentEdittext.text.toString())
            val modal = ShareBottomSheet()
            modal.show(supportFragmentManager, ShareBottomSheet.TAG)
        }
    }

    private fun setSendButton() {
        binding.shareSendButton.text = viewModel.getButtonText()
        binding.shareSendButton.setOnClickListener {
            viewModel.setComment(binding.shareCommentEdittext.text.toString())
            // viewModel의 데이터들 백으로 넘겨주고
            // 메인으로 인텐트 넘겨주기
        }
    }

    companion object {
        const val KEY_MUSIC = "music"
        const val KEY_SINGER = "singer"
    }
}