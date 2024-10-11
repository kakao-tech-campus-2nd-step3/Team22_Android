package com.team22.soundary.feature.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.team22.soundary.databinding.FragmentFriendProfileBinding
import com.team22.soundary.databinding.FragmentMainBinding

class FriendProfileFragment : Fragment() {
    private var _binding: FragmentFriendProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FriendProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFriendProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val friendId = arguments?.getString("FRIEND_ID") ?: return
        viewModel.loadFriendProfile(friendId)

        viewModel.friendProfile.observe(viewLifecycleOwner) { profile ->
            binding.userNameTextview.text = profile.name
            binding.userEmailTextview.text = profile.email
            binding.statusMessageTextview.text = profile.statusMessage
            updateFavoriteGenres(profile.favoriteGenres)
        }

        binding.backIcon.setOnClickListener {
            requireActivity().onBackPressed()
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
