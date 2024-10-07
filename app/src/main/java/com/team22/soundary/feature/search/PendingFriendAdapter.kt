package com.team22.soundary.feature.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.team22.soundary.R
import com.team22.soundary.feature.search.data.model.FriendEntity

class PendingFriendAdapter(
    private val onItemClick: (FriendEntity) -> Unit
) : ListAdapter<FriendEntity, PendingFriendAdapter.PendingFriendViewHolder>(FriendDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendingFriendViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.friend_item_pending, parent, false)
        return PendingFriendViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: PendingFriendViewHolder, position: Int) {
        val friend = getItem(position)
        holder.bind(friend)
    }

    class PendingFriendViewHolder(itemView: View, private val onItemClick: (FriendEntity) -> Unit)
        : RecyclerView.ViewHolder(itemView) {

        private val profileInitialTextView: TextView = itemView.findViewById(R.id.profile_initial_textview)
        private val userNameTextView: TextView = itemView.findViewById(R.id.user_name_textview)
        private var currentFriend: FriendEntity? = null

        init {
            // init 블록에서 setOnClickListener 호출
            itemView.setOnClickListener {
                currentFriend?.let { friend ->
                    onItemClick(friend)
                }
            }
        }

        fun bind(friend: FriendEntity) {
            currentFriend = friend
            profileInitialTextView.text = friend.name.first().toString()
            userNameTextView.text = friend.name
        }
    }

    class FriendDiffCallback : DiffUtil.ItemCallback<FriendEntity>() {
        override fun areItemsTheSame(oldItem: FriendEntity, newItem: FriendEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FriendEntity, newItem: FriendEntity): Boolean {
            return oldItem == newItem
        }
    }
}
