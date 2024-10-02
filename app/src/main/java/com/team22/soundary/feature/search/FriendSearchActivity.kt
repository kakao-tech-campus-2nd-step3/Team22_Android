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

class FriendSearchActivity : AppCompatActivity() {

    private lateinit var newFriendsAdapter: FriendAdapter // 새로운 친구 목록 어댑터
    private lateinit var myFriendsAdapter: FriendAdapter // 내 친구 목록 어댑터
    private lateinit var pendingFriendsAdapter: PendingFriendAdapter // 대기 중인 친구 항목 어뎁터
    private lateinit var binding: ActivityFriendSearchBinding // 바인딩 객체 추가

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 바인딩 객체 획득
        binding = ActivityFriendSearchBinding.inflate(layoutInflater)

        // 액티비티 화면 출력
        setContentView(binding.root)

        // 어댑터 초기화 (리스트 없이 생성)
        newFriendsAdapter = FriendAdapter()
        myFriendsAdapter = FriendAdapter()
        pendingFriendsAdapter = PendingFriendAdapter()

        // 새로운 친구 RecyclerView 설정

        // 새로운 친구 RecyclerView 설정
        binding.newFriendsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.newFriendsRecyclerView.adapter = newFriendsAdapter

        // 내 친구 RecyclerView 설정
        binding.myFriendsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.myFriendsRecyclerView.adapter = myFriendsAdapter

        binding.pendingFriendsRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.pendingFriendsRecyclerView.adapter = pendingFriendsAdapter

        // 대기 중인 친구 섹션 설정
        binding.pendingFriendsHeader.setOnClickListener {
            if (binding.pendingFriendsRecyclerView.visibility == View.GONE) {
                binding.pendingFriendsRecyclerView.visibility = View.VISIBLE
            } else {
                binding.pendingFriendsRecyclerView.visibility = View.GONE
            }
        }

        // 예시 데이터로 어댑터에 데이터 설정
        newFriendsAdapter.submitList(getNewFriendList()) // submitList() 사용
        myFriendsAdapter.submitList(getMyFriendList()) // submitList() 사용
        pendingFriendsAdapter.submitList(getPendingFriendsList()) // 대기 중인 친구 리스트 설정

        updateFriendsCount()

        // 검색 기능 추가
        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // 검색어에 따라 친구 목록을 필터링하는 로직 추가
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

    // 예시 새로운 친구 목록 데이터를 반환하는 함수
    private fun getNewFriendList(): List<FriendEntity> {
        return listOf(
            FriendEntity("김고고", "@gogoKim", "Ballad", true),
            FriendEntity("박고고", "@parkKim", "Jazz", true)
        )
    }

    // 대기 중인 친구 목록 반환
    private fun getPendingFriendsList(): List<FriendEntity> {
        return listOf(
            FriendEntity("어피치", "@apeach", "K-pop", true),
            FriendEntity("라이언", "@ryan", "Jazz", true),
            FriendEntity("콘", "@cony", "Rock", true)
        )
    }

    // 예시 내 친구 목록 데이터를 반환하는 함수
    private fun getMyFriendList(): List<FriendEntity> {
        return listOf(
            FriendEntity("김남남", "@nyamnyam", "R&B", false),
            FriendEntity("이남남", "@nyamnyam_22", "Jpop", false),
            FriendEntity("쿠키즈용", "@kookooyong", "POP", false),
            FriendEntity("쿠키즈", "@kookoo", "POP", false)
        )
    }

    // 친구 목록 수를 업데이트하는 함수
    private fun updateFriendsCount() {
        val myFriendsCount = myFriendsAdapter.itemCount
        binding.friendsListTitle.text = "내 친구 ($myFriendsCount/20)"  // 친구 수 업데이트
    }

    // 친구 목록을 필터링하는 함수 (새로운 친구와 내 친구 모두 필터링)
    private fun filterFriends(query: String) {
        val filteredNewFriends = getNewFriendList().filter { friend ->
            friend.name.contains(query, ignoreCase = true)
        }

        val filteredMyFriends = getMyFriendList().filter { friend ->
            friend.name.contains(query, ignoreCase = true)
        }

        // 필터링된 목록을 각각의 어댑터에 업데이트
        newFriendsAdapter.submitList(filteredNewFriends)
        myFriendsAdapter.submitList(filteredMyFriends)

        // 필터링 후 친구 수 업데이트
        updateFriendsCount()
    }
}
