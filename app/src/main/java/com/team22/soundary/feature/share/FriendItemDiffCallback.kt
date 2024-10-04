package com.team22.soundary.feature.share

import androidx.recyclerview.widget.DiffUtil
import com.team22.soundary.feature.share.data.FriendItemEntity

class FriendItemDiffCallback : DiffUtil.ItemCallback<FriendItemEntity>() {
    override fun areItemsTheSame(oldItem: FriendItemEntity, newItem: FriendItemEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: FriendItemEntity, newItem: FriendItemEntity): Boolean {
        return oldItem == newItem
    }
}