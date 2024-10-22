package com.team22.soundary.feature.share

import androidx.recyclerview.widget.DiffUtil
import com.team22.soundary.core.domain.model.Song

class MusicItemDiffCallback : DiffUtil.ItemCallback<Song>() {
    override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
        return oldItem == newItem
    }
}