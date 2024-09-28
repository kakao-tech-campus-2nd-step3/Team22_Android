package com.team22.soundary.feature.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ExpandableListView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.team22.soundary.R
import com.team22.soundary.databinding.ActivityFriendSearchBinding
import com.team22.soundary.feature.search.data.Friend

class FriendSearchActivity : AppCompatActivity() {

    private lateinit var newFriendsAdapter: FriendAdapter // 새로운 친구 목록 어댑터
    private lateinit var myFriendsAdapter: FriendAdapter // 내 친구 목록 어댑터
    private lateinit var binding: ActivityFriendSearchBinding // 바인딩 객체 추가

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 바인딩 객체 획득
        binding = ActivityFriendSearchBinding.inflate(layoutInflater)

        // 액티비티 화면 출력
        setContentView(binding.root)

        // 새로운 친구 RecyclerView 설정
        binding.newFriendsRecyclerView.layoutManager = LinearLayoutManager(this)
        newFriendsAdapter = FriendAdapter(getNewFriendList()) // 예시 데이터로 새로운 친구 어댑터 설정
        binding.newFriendsRecyclerView.adapter = newFriendsAdapter

        // ExpandableListView 어댑터 설정
        val pendingFriendsAdapter = PendingFriendAdapter("대기중인 친구", getPendingFriendsList())
        binding.expandableListView.setAdapter(pendingFriendsAdapter)

        // ExpandableListView의 그룹 확장 및 축소 리스너
        binding.expandableListView.setOnGroupExpandListener {
            // 그룹이 확장되면 레이아웃을 다시 그려 '내 친구' 목록이 제대로 배치되도록 처리
            binding.myFriendsRecyclerView.invalidate()
        }

        binding.expandableListView.setOnGroupCollapseListener {
            // 그룹이 축소되면 레이아웃을 다시 그려서 업데이트
            binding.myFriendsRecyclerView.invalidate()
        }

        // 내 친구 RecyclerView 설정
        binding.myFriendsRecyclerView.layoutManager = LinearLayoutManager(this)
        myFriendsAdapter = FriendAdapter(getMyFriendList()) // 예시 데이터로 내 친구 어댑터 설정
        binding.myFriendsRecyclerView.adapter = myFriendsAdapter

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
    private fun getNewFriendList(): List<Friend> {
        return listOf(
            Friend("김고고", "@gogoKim", "Ballad", true),
            Friend("박고고", "@parkKim", "Jazz", true)
        )
    }

    // 대기 중인 친구 목록 반환
    private fun getPendingFriendsList(): List<Friend> {
        return listOf(
            Friend("어피치", "@apeach", "K-pop", true),
            Friend("라이언", "@ryan", "Jazz", true),
            Friend("콘", "@cony", "Rock", true)
        )
    }

    // 예시 내 친구 목록 데이터를 반환하는 함수
    private fun getMyFriendList(): List<Friend> {
        return listOf(
            Friend("김남남", "@nyamnyam", "R&B", false),
            Friend("이남남", "@nyamnyam_22", "Jpop", false),
            Friend("쿠키즈용", "@kookooyong", "POP", false),
            Friend("쿠키즈", "@kookoo", "POP", false)
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
        newFriendsAdapter.updateList(filteredNewFriends)
        myFriendsAdapter.updateList(filteredMyFriends)

        // 필터링 후 친구 수 업데이트
        updateFriendsCount()
    }
}
