package com.team22.soundary

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.team22.soundary.feature.search.FriendAdapter
import com.team22.soundary.feature.search.data.Friend

class FriendSearchActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchEditText: EditText
    private lateinit var cancelButton: ImageView
    private lateinit var friendAdapter: FriendAdapter // RecyclerView 어댑터

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_search)

        // UI 요소와 연결
        searchEditText = findViewById(R.id.search_edit_text)
        cancelButton = findViewById(R.id.cancel_button)
        recyclerView = findViewById(R.id.recycler_view)

        // RecyclerView 설정
        recyclerView.layoutManager = LinearLayoutManager(this)
        friendAdapter = FriendAdapter(getFriendList()) // 예시 데이터로 어댑터 설정
        recyclerView.adapter = friendAdapter

        // 검색 기능 추가
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // 검색어에 따라 친구 목록을 필터링하는 로직 추가
                filterFriends(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // 취소 버튼 클릭 시 검색창 초기화
        cancelButton.setOnClickListener {
            searchEditText.text.clear()
        }
    }

    // 예시 친구 목록 데이터를 반환하는 함수
    private fun getFriendList(): List<Friend> {
        return listOf(
            Friend("1", "쿠키즈1", "K-pop"),
            Friend("2", "쿠키즈2", "Jazz"),
            Friend("3", "쿠키즈33", "Rock")
        )
    }

    // 친구 목록을 필터링하는 함수
    private fun filterFriends(query: String) {
        val filteredList = getFriendList().filter { friend ->
            friend.name.contains(query, ignoreCase = true)
        }
        friendAdapter.updateList(filteredList)
    }
}