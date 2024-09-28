package com.team22.soundary.feature.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.team22.soundary.databinding.FriendItemBasicBinding
import com.team22.soundary.databinding.FriendItemNewBinding
import com.team22.soundary.feature.search.data.Friend

class FriendAdapter(private var friends: List<Friend>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_NEW = 1
    private val VIEW_TYPE_BASIC = 2

    override fun getItemViewType(position: Int): Int {
        return if (friends[position].isNewFriend) {
            VIEW_TYPE_NEW
        } else {
            VIEW_TYPE_BASIC
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_NEW -> {
                // NewFriendViewHolder 생성
                val binding = FriendItemNewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                NewFriendViewHolder(binding)
            }
            VIEW_TYPE_BASIC -> {
                // BasicFriendViewHolder 생성 (ViewBinding 사용)
                val binding = FriendItemBasicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                BasicFriendViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val friend = friends[position]
        // ViewHolder 타입에 따라 캐스팅하여 bind() 호출
        when (holder) {
            is NewFriendViewHolder -> holder.bind(friend)
            is BasicFriendViewHolder -> holder.bind(friend)
        }
    }

    override fun getItemCount(): Int {
        return friends.size
    }

    class NewFriendViewHolder(private val binding: FriendItemNewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(friend: Friend) {
            binding.userNameTextview.text = friend.name
            binding.favoriteGenreTextview.text = friend.genre
            binding.profileInitialTextview.text = friend.name.first().toString()

            binding.friendAcceptButton.setOnClickListener {
                // 새로운 친구 수락 로직
            }
            binding.friendDeclineButton.setOnClickListener {
                // 새로운 친구 거절 로직
            }
        }
    }

    // BasicFriendViewHolder에서 ViewBinding을 사용
    class BasicFriendViewHolder(private val binding: FriendItemBasicBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(friend: Friend) {
            binding.userNameTextview.text = friend.name
            binding.favoriteGenreTextview.text = friend.genre
            binding.profileInitialTextview.text = friend.name.first().toString()

            binding.friendDeleteButton.setOnClickListener {
                // 친구 삭제 로직
            }
        }
    }

    fun updateList(newFriends: List<Friend>) {
        friends = newFriends
        notifyDataSetChanged()
    }
}