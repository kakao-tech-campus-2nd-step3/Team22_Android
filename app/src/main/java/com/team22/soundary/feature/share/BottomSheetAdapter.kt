package com.team22.soundary.feature.share

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.team22.soundary.R
import com.team22.soundary.databinding.ShareFriendItemNoImageBinding
import com.team22.soundary.databinding.ShareFriendItemWithImageBinding
import com.team22.soundary.feature.share.data.FriendItemEntity

class BottomSheetAdapter(
    private val listener: FriendItemClickListener
) : ListAdapter<FriendItemEntity, RecyclerView.ViewHolder>(FriendItemDiffCallback()) {
    class ViewHolderNoImage(
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
            binding.shareGrayBackground.visibility =
                if (friendItem.isSelected) View.VISIBLE else View.INVISIBLE
            item = friendItem
        }
    }

    class ViewHolderWithImage(
        private val binding: ShareFriendItemWithImageBinding,
        private val listener: FriendItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        lateinit var item: FriendItemEntity

        init {
            binding.root.setOnClickListener {
                listener.onClick(it, item)
            }
        }

        fun bind(friendItem: FriendItemEntity) {
            binding.shareFriendImage.setImageResource(R.drawable.stalker)
            binding.shareFriendTextview.text = friendItem.name
            binding.shareGrayBackground.visibility =
                if (friendItem.isSelected) View.VISIBLE else View.INVISIBLE
            item = friendItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            NO_IMAGE -> {
                val binding = ShareFriendItemNoImageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ViewHolderNoImage(binding, listener)
            }

            WITH_IMAGE -> {
                val binding = ShareFriendItemWithImageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ViewHolderWithImage(binding, listener)
            }

            else -> throw IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolderNoImage -> holder.bind(getItem(position))
            is ViewHolderWithImage -> holder.bind(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).image == null) {
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

interface FriendItemClickListener {
    fun onClick(v: View, selectItem: FriendItemEntity)
}