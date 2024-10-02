package com.team22.soundary.feature.search

import FriendAdapter
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.team22.soundary.databinding.ActivityFriendSearchBinding
import com.team22.soundary.feature.search.data.FriendEntity
import com.team22.soundary.feature.search.data.FriendRepository

class FriendSearchActivity : AppCompatActivity() {

    private lateinit var newFriendsAdapter: FriendAdapter // 새로운 친구 목록 어댑터
    private lateinit var myFriendsAdapter: FriendAdapter // 내 친구 목록 어댑터
    private lateinit var pendingFriendsAdapter: PendingFriendAdapter // 대기 중인 친구 어댑터
    private lateinit var binding: ActivityFriendSearchBinding // 바인딩 객체
    private lateinit var friendRepository: FriendRepository // FriendRepository 추가

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 바인딩 객체 획득
        binding = ActivityFriendSearchBinding.inflate(layoutInflater)

        // 액티비티 화면 출력
        setContentView(binding.root)

        // FriendRepository 초기화
        friendRepository = FriendRepository()

        // 어댑터 초기화
        newFriendsAdapter = FriendAdapter()
        myFriendsAdapter = FriendAdapter()
        pendingFriendsAdapter = PendingFriendAdapter()

        // RecyclerView 설정
        binding.newFriendsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.newFriendsRecyclerView.adapter = newFriendsAdapter

        binding.myFriendsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.myFriendsRecyclerView.adapter = myFriendsAdapter

        binding.pendingFriendsRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.pendingFriendsRecyclerView.adapter = pendingFriendsAdapter

        // 대기 중인 친구 섹션 토글 기능
        binding.pendingFriendsHeader.setOnClickListener {
            binding.pendingFriendsRecyclerView.visibility = if (binding.pendingFriendsRecyclerView.visibility == View.GONE) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }

        // FriendRepository에서 데이터 가져와서 어댑터에 설정
        newFriendsAdapter.submitList(friendRepository.getNewFriends())
        myFriendsAdapter.submitList(friendRepository.getMyFriends())
        pendingFriendsAdapter.submitList(friendRepository.getPendingFriends())

        updateFriendsCount()

        // 검색 기능 추가
        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                filterFriends(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // 취소 버튼 클릭 시 검색창 초기화
        binding.cancelButton.setOnClickListener {
            binding.searchEditText.text.clear()
        }
    }

    // 친구 수 업데이트 함수
    private fun updateFriendsCount() {
        val myFriendsCount = myFriendsAdapter.itemCount
        binding.friendsListTitle.text = "내 친구 ($myFriendsCount/20)"
    }

    // 친구 목록 필터링 함수
    private fun filterFriends(query: String) {
        val filteredNewFriends = friendRepository.getNewFriends().filter { friend ->
            friend.name.contains(query, ignoreCase = true)
        }

        val filteredMyFriends = friendRepository.getMyFriends().filter { friend ->
            friend.name.contains(query, ignoreCase = true)
        }

        newFriendsAdapter.submitList(filteredNewFriends)
        myFriendsAdapter.submitList(filteredMyFriends)

        updateFriendsCount()
    }
}
