package com.team22.soundary.feature.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.team22.soundary.R
import com.team22.soundary.databinding.FragmentFriendSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FriendSearchFragment : Fragment() {

    private lateinit var newFriendsAdapter: FriendAdapter
    private lateinit var myFriendsAdapter: FriendAdapter
    private lateinit var pendingFriendsAdapter: PendingFriendAdapter
    private var _binding: FragmentFriendSearchBinding? = null
    private val binding get() = _binding!!

    private val friendSearchViewModel: FriendSearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFriendSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 새로운 친구 어댑터 초기화 (수락/거절 콜백 포함)
        newFriendsAdapter = FriendAdapter(
            onItemClick = { friend ->
                // 친구 프로필 화면으로 이동
                val fragment = FriendProfileFragment().apply {
                    arguments = Bundle().apply {
                        putString("FRIEND_ID", friend.id)
                    }
                }
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.frame, fragment)
                    .addToBackStack(null)
                    .commit()
            },
            onAcceptClick = { friend ->
                friendSearchViewModel.acceptFriend(friend)
            },
            onDeclineClick = { friend ->
                friendSearchViewModel.declineFriend(friend)
            }
        )

        // 내 친구 어댑터 초기화 (삭제 콜백 포함)
        myFriendsAdapter = FriendAdapter(
            onItemClick = { friend ->
                // 친구 프로필 화면으로 이동
                val fragment = FriendProfileFragment().apply {
                    arguments = Bundle().apply {
                        putString("FRIEND_ID", friend.id)
                    }
                }
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.frame, fragment)
                    .addToBackStack(null)
                    .commit()
            },
            onDeleteClick = { friend ->
                friendSearchViewModel.deleteFriend(friend)
            }
        )

        // 대기 중인 친구 어댑터 초기화
        pendingFriendsAdapter = PendingFriendAdapter { friend ->
            // 친구 프로필 화면으로 이동
            val fragment = FriendProfileFragment().apply {
                arguments = Bundle().apply {
                    putString("FRIEND_ID", friend.id)
                }
            }
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.frame, fragment)
                .addToBackStack(null)
                .commit()
        }

        // RecyclerView 설정
        binding.newFriendsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.newFriendsRecyclerView.adapter = newFriendsAdapter

        binding.myFriendsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.myFriendsRecyclerView.adapter = myFriendsAdapter

        binding.pendingFriendsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.pendingFriendsRecyclerView.adapter = pendingFriendsAdapter

        // 대기 중인 친구 섹션 토글 기능
        binding.pendingFriendsHeader.setOnClickListener {
            binding.pendingFriendsRecyclerView.visibility =
                if (binding.pendingFriendsRecyclerView.visibility == View.GONE) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
        }

        // ViewModel의 StateFlow 관찰
        observeViewModel()

        // 검색 기능 추가
        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString()
                if (query.isEmpty()) {
                    friendSearchViewModel.resetFilters()
                } else {
                    friendSearchViewModel.filterFriends(query)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // 취소 버튼 클릭 시 검색창 초기화
        binding.cancelButton.setOnClickListener {
            binding.searchEditText.text.clear()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            friendSearchViewModel.newFriends.collectLatest { newFriends ->
                newFriendsAdapter.submitList(newFriends)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            friendSearchViewModel.myFriends.collectLatest { myFriends ->
                myFriendsAdapter.submitList(myFriends)
                updateFriendsCount(myFriends.size)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            friendSearchViewModel.pendingFriends.collectLatest { pendingFriends ->
                pendingFriendsAdapter.submitList(pendingFriends)
            }
        }
    }

    private fun updateFriendsCount(count: Int) {
        binding.friendsListTitle.text = "내 친구 ($count/20)"
    }
}
