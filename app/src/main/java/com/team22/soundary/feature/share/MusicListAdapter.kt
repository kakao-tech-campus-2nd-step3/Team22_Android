package com.team22.soundary.feature.share

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.team22.soundary.databinding.ShareMusicItemBinding
import com.team22.soundary.feature.share.domain.Music

class MusicListAdapter(
    private val listener: MusicItemClickListener
) : ListAdapter<Music, MusicListAdapter.ViewHolder>(MusicItemDiffCallback()) {
    class ViewHolder(
        private val binding: ShareMusicItemBinding,
        private val listener: MusicItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        lateinit var item: Music

        init {
            binding.root.setOnClickListener {
                listener.onClick(it, item)
            }
        }

        fun bind(musicItem: Music) {
            binding.shareMusicTextview.text = musicItem.title
            binding.shareSingerTextview.text = musicItem.artists.joinToString(", ")
            //binding.shareSortTextview.text = musicItem.sortValue
            item = musicItem
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
    fun onClick(v: View, selectItem: Music)
}