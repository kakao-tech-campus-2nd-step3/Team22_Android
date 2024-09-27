package com.team22.soundary.feature.share

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.team22.soundary.R
import com.team22.soundary.feature.share.data.FriendItemEntity

class FriendListAdapter(
    private var friendItemList: List<FriendItemEntity>,
    private val layoutInflater: LayoutInflater
) : RecyclerView.Adapter<FriendListAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView

        init {
            nameTextView = itemView.findViewById<TextView>(R.id.share_friend_textview)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = layoutInflater.inflate(R.layout.share_friend_item_no_image, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameTextView.text = friendItemList[position].name
    }

    override fun getItemCount(): Int {
        return friendItemList.size
    }
}