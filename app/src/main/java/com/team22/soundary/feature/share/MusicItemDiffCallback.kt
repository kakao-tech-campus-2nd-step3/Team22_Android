package com.team22.soundary.feature.share

import androidx.recyclerview.widget.DiffUtil
import com.team22.soundary.feature.share.data.MusicItemEntity

class MusicItemDiffCallback : DiffUtil.ItemCallback<MusicItemEntity>() {
    override fun areItemsTheSame(oldItem: MusicItemEntity, newItem: MusicItemEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MusicItemEntity, newItem: MusicItemEntity): Boolean {
        return oldItem == newItem
    }
}