package com.team22.soundary.feature.share

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.team22.soundary.R
import com.team22.soundary.feature.share.data.MusicItemEntity

class MusicListAdapter(
    private var musicItemList: List<MusicItemEntity>,
    private val listener: ItemClickListener
) : RecyclerView.Adapter<MusicListAdapter.ViewHolder>() {
    class ViewHolder(itemView: View, private val listener: ItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        private val musicTextView: TextView
        private val singerTextView: TextView
        private val sortTextView: TextView
        lateinit var item : MusicItemEntity

        init {
            musicTextView = itemView.findViewById<TextView>(R.id.share_music_textview)
            singerTextView = itemView.findViewById<TextView>(R.id.share_singer_textview)
            sortTextView = itemView.findViewById<TextView>(R.id.share_sort_textview)
            itemView.setOnClickListener {
                listener.onClick(it, item)
            }
        }

        fun bind(musicItem: MusicItemEntity) {
            musicTextView.text = musicItem.music
            singerTextView.text = musicItem.singer
            sortTextView.text = musicItem.sortValue
            item = musicItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.share_music_item, parent, false)
        return ViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(musicItemList[position])
    }

    override fun getItemCount(): Int {
        return musicItemList.size
    }
}

interface ItemClickListener {
    fun onClick(v: View, selectItem: MusicItemEntity)
}