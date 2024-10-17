package com.team22.soundary.feature.share

import androidx.recyclerview.widget.DiffUtil
import com.team22.soundary.core.model.User

class FriendItemDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}