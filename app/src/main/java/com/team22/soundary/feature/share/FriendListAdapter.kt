package com.team22.soundary.feature.share

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.team22.soundary.R
import com.team22.soundary.databinding.ShareFriendItemNoImageBinding
import com.team22.soundary.databinding.ShareFriendItemWithImageBinding
import com.team22.soundary.feature.share.data.FriendItemEntity

class FriendListAdapter(
    private var friendItemList: List<FriendItemEntity>,
) : ListAdapter<FriendItemEntity, RecyclerView.ViewHolder>(FriendItemDiffCallback()) {
    class ViewHolderNoImage(
        private val binding: ShareFriendItemNoImageBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(friendItem: FriendItemEntity) {
            binding.shareFriendImage.text = friendItem.name[0].toString()
            binding.shareFriendTextview.text = friendItem.name
        }
    }

    class ViewHolderWithImage(
        private val binding: ShareFriendItemWithImageBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(friendItem: FriendItemEntity) {
            binding.shareFriendImage.setImageResource(R.drawable.stalker)
            binding.shareFriendTextview.text = friendItem.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            0 -> {
                val binding = ShareFriendItemNoImageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ViewHolderNoImage(binding)
            }
            1 -> {
                val binding = ShareFriendItemWithImageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ViewHolderWithImage(binding)
            }
            else -> throw IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolderNoImage -> holder.bind(friendItemList[position])
            is ViewHolderWithImage -> holder.bind(friendItemList[position])
        }
    }

    override fun getItemCount(): Int {
        return friendItemList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if(friendItemList[position].image == null) {
            NO_IMAGE
        } else {
            WITH_IMAGE
        }
    }

    companion object {
        const val NO_IMAGE = 0
        const val WITH_IMAGE = 1
    }
}

