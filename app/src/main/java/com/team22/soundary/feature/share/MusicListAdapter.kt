package com.team22.soundary.feature.share

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.team22.soundary.core.domain.model.Song
import com.team22.soundary.databinding.ShareMusicItemBinding

class MusicListAdapter(
    private val listener: MusicItemClickListener
) : ListAdapter<Song, MusicListAdapter.ViewHolder>(MusicItemDiffCallback()) {
    class ViewHolder(
        private val binding: ShareMusicItemBinding,
        private val listener: MusicItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        lateinit var item: Song

        init {
            binding.root.setOnClickListener {
                listener.onClick(it, item)
            }
        }

        fun bind(songItem: Song) {
            binding.shareMusicTextview.text = songItem.title
            binding.shareSingerTextview.text = songItem.artist.joinToString(", ")
            binding.shareSortTextview.text = "musicItem.sortValue"
            item = songItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ShareMusicItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

interface MusicItemClickListener {
    fun onClick(v: View, selectItem: Song)
}