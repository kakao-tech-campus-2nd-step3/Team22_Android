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
) : RecyclerView.Adapter<MusicListAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val musicTextView: TextView
        val singerTextView: TextView
        val sortTextView: TextView

        init {
            musicTextView = itemView.findViewById<TextView>(R.id.share_music_textview)
            singerTextView = itemView.findViewById<TextView>(R.id.share_singer_textview)
            sortTextView = itemView.findViewById<TextView>(R.id.share_sort_textview)
            itemView.setOnClickListener {
                itemListener.onClick(it, musicItemList[adapterPosition])
            }
        }
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemListener = itemClickListener
    }

    lateinit var itemListener: ItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.share_music_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.musicTextView.text = musicItemList[position].music
        holder.singerTextView.text = musicItemList[position].singer
        holder.sortTextView.text = musicItemList[position].sortValue
    }

    override fun getItemCount(): Int {
        return musicItemList.size
    }
}

interface ItemClickListener {
    fun onClick(v: View, selectItem: MusicItemEntity)
}