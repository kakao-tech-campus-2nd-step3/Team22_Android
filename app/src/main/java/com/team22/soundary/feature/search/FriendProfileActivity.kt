package com.team22.soundary.feature.search

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.team22.soundary.databinding.ActivityFriendProfileBinding

class FriendProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFriendProfileBinding
    private val viewModel: FriendProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFriendProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val friendId = intent.getStringExtra("FRIEND_ID") ?: return

        viewModel.loadFriendProfile(friendId)

        viewModel.friendProfile.observe(this) { profile ->
            binding.userNameTextview.text = profile.name
            binding.userEmailTextview.text = profile.email
            binding.statusMessageTextview.text = profile.statusMessage
            updateFavoriteGenres(profile.favoriteGenres)
        }

        binding.backIcon.setOnClickListener {
            finish()
        }

        binding.addFriendButton.setOnClickListener {
            // 친구 추가 로직 처리
        }
    }

    private fun updateFavoriteGenres(genres: List<String>) {
        val genreTextViews = listOf(
            binding.genreTextView1,
            binding.genreTextView2,
            binding.genreTextView3,
            binding.genreTextView4
        )

        for (i in genreTextViews.indices) {
            if (i < genres.size) {
                genreTextViews[i].text = genres[i]
                genreTextViews[i].visibility = View.VISIBLE
            } else {
                genreTextViews[i].visibility = View.GONE
            }
        }
    }
}
