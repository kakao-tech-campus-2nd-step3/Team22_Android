package com.team22.soundary.feature.share

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.team22.soundary.databinding.ShareFriendItemNoImageBinding
import com.team22.soundary.feature.share.data.FriendItemEntity

class BottomSheetAdapter(
    private var friendItemList: List<FriendItemEntity>,
    private val listener: FriendItemClickListener
) : RecyclerView.Adapter<BottomSheetAdapter.ViewHolder>() {
    class ViewHolder(
        private val binding: ShareFriendItemNoImageBinding,
        private val listener: FriendItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        lateinit var item: FriendItemEntity

        init {
            binding.root.setOnClickListener {
                listener.onClick(it, item)
            }
        }

        fun bind(friendItem: FriendItemEntity) {
            binding.shareFriendImage.text = friendItem.name[0].toString()
            binding.shareFriendTextview.text = friendItem.name
            item = friendItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ShareFriendItemNoImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(friendItemList[position])
    }

    override fun getItemCount(): Int {
        return friendItemList.size
    }
}

interface FriendItemClickListener {
    fun onClick(v: View, selectItem: FriendItemEntity)
}