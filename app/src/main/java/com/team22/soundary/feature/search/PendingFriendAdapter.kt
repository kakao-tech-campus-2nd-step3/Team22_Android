package com.team22.soundary.feature.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.team22.soundary.R
import com.team22.soundary.feature.search.data.Friend

class PendingFriendAdapter :
    ListAdapter<Friend, PendingFriendAdapter.PendingFriendViewHolder>(FriendDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendingFriendViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.friend_item_pending, parent, false)
        return PendingFriendViewHolder(view)
    }

    override fun onBindViewHolder(holder: PendingFriendViewHolder, position: Int) {
        val friend = getItem(position)
        holder.bind(friend)
    }

    // ViewHolder 정의
    class PendingFriendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val profileInitialTextView: TextView = itemView.findViewById(R.id.profile_initial_textview)
        private val userNameTextView: TextView = itemView.findViewById(R.id.user_name_textview)

        fun bind(friend: Friend) {
            profileInitialTextView.text = friend.name.first().toString()
            userNameTextView.text = friend.name
        }
    }

    // DiffUtil.ItemCallback 구현
    class FriendDiffCallback : DiffUtil.ItemCallback<Friend>() {
        override fun areItemsTheSame(oldItem: Friend, newItem: Friend): Boolean {
            // 각 Friend 객체의 고유 ID를 비교하여 동일한 항목인지 확인
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Friend, newItem: Friend): Boolean {
            // 객체의 내용이 동일한지 비교
            return oldItem == newItem
        }
    }
}
