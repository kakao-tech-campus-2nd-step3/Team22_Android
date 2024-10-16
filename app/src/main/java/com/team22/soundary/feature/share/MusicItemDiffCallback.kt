package com.team22.soundary.feature.share

import androidx.recyclerview.widget.DiffUtil
import com.team22.soundary.feature.share.domain.Music

class MusicItemDiffCallback : DiffUtil.ItemCallback<Music>() {
    override fun areItemsTheSame(oldItem: Music, newItem: Music): Boolean {
        return oldItem.platformTrackId == newItem.platformTrackId
    }

    override fun areContentsTheSame(oldItem: Music, newItem: Music): Boolean {
        return oldItem == newItem
    }
}