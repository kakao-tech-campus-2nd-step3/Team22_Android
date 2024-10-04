package com.team22.soundary.feature.search

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.team22.soundary.databinding.ActivityFriendSearchBinding
import com.team22.soundary.feature.search.FriendProfileActivity

class FriendSearchActivity : AppCompatActivity() {

    private lateinit var newFriendsAdapter: FriendAdapter
    private lateinit var myFriendsAdapter: FriendAdapter
    private lateinit var pendingFriendsAdapter: PendingFriendAdapter
    private lateinit var binding: ActivityFriendSearchBinding

    private val friendSearchViewModel: FriendSearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFriendSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 어댑터 초기화 시 클릭 리스너 전달
        newFriendsAdapter = FriendAdapter { friend ->
            val intent = Intent(this, FriendProfileActivity::class.java)
            intent.putExtra("FRIEND_ID", friend.id)
            startActivity(intent)
        }

        myFriendsAdapter = FriendAdapter { friend ->
            val intent = Intent(this, FriendProfileActivity::class.java)
            intent.putExtra("FRIEND_ID", friend.id)
            startActivity(intent)
        }

        pendingFriendsAdapter = PendingFriendAdapter { friend ->
            val intent = Intent(this, FriendProfileActivity::class.java)
            intent.putExtra("FRIEND_ID", friend.id)
            startActivity(intent)
        }

        // RecyclerView 설정
        binding.newFriendsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.newFriendsRecyclerView.adapter = newFriendsAdapter

        binding.myFriendsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.myFriendsRecyclerView.adapter = myFriendsAdapter

        binding.pendingFriendsRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
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

        // ViewModel의 LiveData 관찰
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

    private fun observeViewModel() {
        friendSearchViewModel.newFriends.observe(this) { newFriends ->
            newFriendsAdapter.submitList(newFriends)
        }

        friendSearchViewModel.myFriends.observe(this) { myFriends ->
            myFriendsAdapter.submitList(myFriends)
            updateFriendsCount()
        }

        friendSearchViewModel.pendingFriends.observe(this) { pendingFriends ->
            pendingFriendsAdapter.submitList(pendingFriends)
        }
    }

    private fun updateFriendsCount() {
        val myFriendsCount = myFriendsAdapter.itemCount
        binding.friendsListTitle.text = "내 친구 ($myFriendsCount/20)"
    }
}
