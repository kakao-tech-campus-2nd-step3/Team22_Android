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
    private var friendItemList: List<FriendItemEntity>,
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
            binding.shareGrayBackground.visibility = if(friendItem.isSelected) View.VISIBLE else View.INVISIBLE
            Log.d("uin", ""+binding.shareGrayBackground.visibility)
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
            binding.shareGrayBackground.visibility = if(friendItem.isSelected) View.VISIBLE else View.INVISIBLE
            item = friendItem
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
                return ViewHolderNoImage(binding, listener)
            }
            1 -> {
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
            is ViewHolderNoImage -> holder.bind(friendItemList[position])
            is ViewHolderWithImage -> holder.bind(friendItemList[position])
        }
    }

    override fun getItemCount(): Int {
        return friendItemList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if(friendItemList[position].image == null) {
            FriendListAdapter.NO_IMAGE
        } else {
            FriendListAdapter.WITH_IMAGE
        }
    }
}

interface FriendItemClickListener {
    fun onClick(v: View, selectItem: FriendItemEntity)
    //fun onClick(v: View, position: Int)
}