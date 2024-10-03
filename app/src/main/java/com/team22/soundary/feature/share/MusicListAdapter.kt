package com.team22.soundary.feature.share

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.team22.soundary.databinding.ShareMusicItemBinding
import com.team22.soundary.feature.share.data.MusicItemEntity

class MusicListAdapter(
    private var musicItemList: List<MusicItemEntity>,
    private val listener: MusicItemClickListener
) : ListAdapter<MusicItemEntity, MusicListAdapter.ViewHolder>(MusicItemDiffCallback()) {
    class ViewHolder(
        private val binding: ShareMusicItemBinding,
        private val listener: MusicItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        lateinit var item: MusicItemEntity

        init {
            binding.root.setOnClickListener {
                listener.onClick(it, item)
            }
        }

        fun bind(musicItem: MusicItemEntity) {
            binding.shareMusicTextview.text = musicItem.music
            binding.shareSingerTextview.text = musicItem.singer
            binding.shareSortTextview.text = musicItem.sortValue
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
        holder.bind(musicItemList[position])
    }

    override fun getItemCount(): Int {
        return musicItemList.size
    }
}

interface MusicItemClickListener {
    fun onClick(v: View, selectItem: MusicItemEntity)
}