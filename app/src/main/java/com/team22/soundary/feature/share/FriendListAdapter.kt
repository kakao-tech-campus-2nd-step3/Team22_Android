package com.team22.soundary.feature.share

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.team22.soundary.databinding.ShareFriendItemNoImageBinding
import com.team22.soundary.feature.share.data.FriendItemEntity

class FriendListAdapter(
    private var friendItemList: List<FriendItemEntity>,
) : RecyclerView.Adapter<FriendListAdapter.ViewHolder>() {
    class ViewHolder(
        private val binding: ShareFriendItemNoImageBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(friendItem: FriendItemEntity) {
            binding.shareFriendImage.text = friendItem.name[0].toString()
            binding.shareFriendTextview.text = friendItem.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ShareFriendItemNoImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(friendItemList[position])
    }

    override fun getItemCount(): Int {
        return friendItemList.size
    }
}