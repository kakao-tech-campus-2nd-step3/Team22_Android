package com.team22.soundary.feature.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.team22.soundary.R
import com.team22.soundary.feature.search.data.Friend

class FriendAdapter(private var friends: List<Friend>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_NEW = 1
    private val VIEW_TYPE_REGULAR = 2

    override fun getItemViewType(position: Int): Int {
        return if (friends[position].isNewFriend) {
            VIEW_TYPE_NEW
        } else {
            VIEW_TYPE_REGULAR
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_NEW) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.friend_item_new, parent, false)
            NewFriendViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.friend_item_basic, parent, false)
            RegularFriendViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val friend = friends[position]
        if (holder is NewFriendViewHolder) {
            holder.bind(friend)
        } else if (holder is RegularFriendViewHolder) {
            holder.bind(friend)
        }
    }

    override fun getItemCount() = friends.size

    class NewFriendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.user_name_textview)
        private val genreTextView: TextView = itemView.findViewById(R.id.favorite_genre_textview)
        private val profileInitialTextView: TextView = itemView.findViewById(R.id.profile_initial_textview)
        private val acceptButton: Button = itemView.findViewById(R.id.friend_accept_button)
        private val declineButton: Button = itemView.findViewById(R.id.friend_decline_button)

        fun bind(friend: Friend) {
            nameTextView.text = friend.name
            genreTextView.text = friend.genre
            profileInitialTextView.text = friend.name.first().toString()

            // 수락, 거절 버튼 클릭 리스너 추가
            acceptButton.setOnClickListener {
                // 새로운 친구 수락 로직
            }
            declineButton.setOnClickListener {
                // 새로운 친구 거절 로직
            }
        }
    }

    class RegularFriendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.user_name_textview)
        private val genreTextView: TextView = itemView.findViewById(R.id.favorite_genre_textview)
        private val profileInitialTextView: TextView = itemView.findViewById(R.id.profile_initial_textview)
        private val deleteButton: Button = itemView.findViewById(R.id.friend_delete_button)

        fun bind(friend: Friend) {
            nameTextView.text = friend.name
            genreTextView.text = friend.genre
            profileInitialTextView.text = friend.name.first().toString()

            // 삭제 버튼 클릭 리스너 추가
            deleteButton.setOnClickListener {
                // 친구 삭제 로직
            }
        }
    }

    fun updateList(newFriends: List<Friend>) {
        friends = newFriends
        notifyDataSetChanged()
    }
}