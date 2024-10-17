package com.team22.soundary.feature.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.team22.soundary.R
import com.team22.soundary.core.model.User

class PendingFriendAdapter(
    private val onItemClick: (User) -> Unit
) : ListAdapter<User, PendingFriendAdapter.PendingFriendViewHolder>(FriendDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendingFriendViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.friend_item_pending, parent, false)
        return PendingFriendViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: PendingFriendViewHolder, position: Int) {
        val friend = getItem(position)
        holder.bind(friend)
    }

    class PendingFriendViewHolder(itemView: View, private val onItemClick: (User) -> Unit)
        : RecyclerView.ViewHolder(itemView) {

        private val profileInitialTextView: TextView = itemView.findViewById(R.id.profile_initial_textview)
        private val userNameTextView: TextView = itemView.findViewById(R.id.user_name_textview)
        private var currentFriend: User? = null

        init {
            // init 블록에서 setOnClickListener 호출
            itemView.setOnClickListener {
                currentFriend?.let { friend ->
                    onItemClick(friend)
                }
            }
        }

        fun bind(friend: User) {
            currentFriend = friend
            profileInitialTextView.text = friend.name.first().toString()
            userNameTextView.text = friend.name
        }
    }

    class FriendDiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }
}
