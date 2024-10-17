package com.team22.soundary.feature.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.team22.soundary.core.model.User
import com.team22.soundary.databinding.FriendItemBasicBinding
import com.team22.soundary.databinding.FriendItemNewBinding

class FriendAdapter(
    private val onItemClick: (User) -> Unit,
    private val onAcceptClick: ((User) -> Unit)? = null,
    private val onDeclineClick: ((User) -> Unit)? = null,
    private val onDeleteClick: ((User) -> Unit)? = null
) : ListAdapter<User, RecyclerView.ViewHolder>(FriendDiffCallback()) {

    private val VIEW_TYPE_NEW = 1
    private val VIEW_TYPE_BASIC = 2

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).status == "requested") {
            VIEW_TYPE_NEW // 나에게 친구 요청을 보낸 친구
        } else {
            VIEW_TYPE_BASIC // 친구 상태인 친구
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_NEW -> {
                val binding = FriendItemNewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                NewFriendViewHolder(binding, onItemClick, onAcceptClick, onDeclineClick)
            }
            VIEW_TYPE_BASIC -> {
                val binding = FriendItemBasicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                BasicFriendViewHolder(binding, onItemClick, onDeleteClick)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val friend = getItem(position)
        when (holder) {
            is NewFriendViewHolder -> holder.bind(friend)
            is BasicFriendViewHolder -> holder.bind(friend)
        }
    }
}

class BasicFriendViewHolder(
    private val binding: FriendItemBasicBinding,
    private val onItemClick: (User) -> Unit,
    private val onDeleteClick: ((User) -> Unit)?
) : RecyclerView.ViewHolder(binding.root) {

    private var currentFriend: User? = null

    init {
        // init 블록 안에서 setOnclickListener 호출
        binding.friendDeleteButton.setOnClickListener {
            currentFriend?.let { friend ->
                onDeleteClick?.invoke(friend)
            }
        }

        binding.root.setOnClickListener {
            currentFriend?.let { friend ->
                onItemClick(friend)
            }
        }
    }

    fun bind(friend: User) {
        currentFriend = friend
        binding.userNameTextview.text = friend.name
        binding.profileInitialTextview.text = friend.name.first().toString()

        val firstGenre = friend.category.firstOrNull() ?: "장르 없음"
        binding.favoriteGenreTextview.text = firstGenre.toString()

        binding.userIdTextview.text = "@${friend.id}"
    }
}

class NewFriendViewHolder(
    private val binding: FriendItemNewBinding,
    private val onItemClick: (User) -> Unit,
    private val onAcceptClick: ((User) -> Unit)?,
    private val onDeclineClick: ((User) -> Unit)?
) : RecyclerView.ViewHolder(binding.root) {

    private var currentFriend: User? = null

    init {
        // init 블록 안에서 setOnclickListener 호출
        binding.friendAcceptButton.setOnClickListener {
            currentFriend?.let { friend ->
                onAcceptClick?.invoke(friend)
            }
        }

        binding.friendDeclineButton.setOnClickListener {
            currentFriend?.let { friend ->
                onDeclineClick?.invoke(friend)
            }
        }

        binding.root.setOnClickListener {
            currentFriend?.let { friend ->
                onItemClick(friend)
            }
        }
    }

    fun bind(friend: User) {
        currentFriend = friend
        binding.userNameTextview.text = friend.name
        binding.profileInitialTextview.text = friend.name.first().toString()

        val firstGenre = friend.category.firstOrNull() ?: "장르 없음"
        binding.favoriteGenreTextview.text = firstGenre.toString()

        binding.userIdTextview.text = "@${friend.id}"
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
