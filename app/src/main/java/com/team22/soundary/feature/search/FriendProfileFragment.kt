package com.team22.soundary.feature.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.team22.soundary.core.domain.model.Category
import com.team22.soundary.databinding.FragmentFriendProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FriendProfileFragment : Fragment() {
    private var _binding: FragmentFriendProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FriendProfileViewModel by viewModels()
    private lateinit var friendId: String

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

        friendId = arguments?.getString("FRIEND_ID") ?: return
        viewModel.loadFriendProfile(friendId)

        // 프로필 정보 관찰 및 UI 업데이트
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.friendProfile.collectLatest { profile ->
                profile?.let {
                    binding.userNameTextview.text = it.name
                    binding.userEmailTextview.text = it.email
                    binding.statusMessageTextview.text = it.statusMessage
                    updateFavoriteGenres(it.category)

                    // 친구 상태에 따라 버튼 업데이트
                    when (it.status) {
                        "accepted" -> {
                            binding.addFriendButton.isEnabled = false
                            binding.addFriendButton.text = "이미 친구입니다"
                        }
                        "pending" -> {
                            binding.addFriendButton.isEnabled = false
                            binding.addFriendButton.text = "친구 요청 중"
                        }
                        "requested" -> {
                            binding.addFriendButton.isEnabled = true
                            binding.addFriendButton.text = "친구 요청 수락"
                            binding.addFriendButton.setOnClickListener {
                                viewModel.acceptFriend(friendId)
                                Toast.makeText(requireContext(), "친구 요청을 수락했습니다.", Toast.LENGTH_SHORT).show()
                            }
                        }
                        else -> {
                            binding.addFriendButton.isEnabled = true
                            binding.addFriendButton.text = "친구 추가"
                            binding.addFriendButton.setOnClickListener {
                                viewModel.addFriend(friendId)
                            }
                        }
                    }
                }
            }
        }

        // 친구 추가 결과 관찰
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isFriendAdded.collectLatest { isAdded ->
                if (isAdded) {
                    // 친구 프로필을 다시 로드하여 상태 업데이트
                    viewModel.loadFriendProfile(friendId)
                }
            }
        }

        binding.backIcon.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun updateFavoriteGenres(genres: List<Category>) {
        val genreTextViews = listOf(
            binding.genreTextView1,
            binding.genreTextView2,
            binding.genreTextView3,
            binding.genreTextView4
        )

        for (i in genreTextViews.indices) {
            if (i < genres.size) {
                genreTextViews[i].text = genres[i].toString()
                genreTextViews[i].visibility = View.VISIBLE
            } else {
                genreTextViews[i].visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
