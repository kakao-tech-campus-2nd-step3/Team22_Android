package com.team22.soundary.feature.share

import androidx.recyclerview.widget.DiffUtil
import com.team22.soundary.feature.share.domain.Friend

class FriendItemDiffCallback : DiffUtil.ItemCallback<Friend>() {
    override fun areItemsTheSame(oldItem: Friend, newItem: Friend): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Friend, newItem: Friend): Boolean {
        return oldItem == newItem
    }
}