package com.team22.soundary.feature.share

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.team22.soundary.R
import com.team22.soundary.core.model.User
import com.team22.soundary.databinding.ShareFriendItemNoImageBinding
import com.team22.soundary.databinding.ShareFriendItemWithImageBinding

class FriendListAdapter :
    ListAdapter<User, RecyclerView.ViewHolder>(FriendItemDiffCallback()) {
    class ViewHolderNoImage(
        private val binding: ShareFriendItemNoImageBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(userItem: User) {
            binding.shareFriendImage.text = userItem.name[0].toString()
            binding.shareFriendTextview.text = userItem.name
            binding.shareGrayBackground.visibility = View.INVISIBLE
        }
    }

    class ViewHolderWithImage(
        private val binding: ShareFriendItemWithImageBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(userItem: User) {
            binding.shareFriendImage.setImageResource(R.drawable.stalker)
            binding.shareFriendTextview.text = userItem.name
            binding.shareGrayBackground.visibility = View.INVISIBLE
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
                return ViewHolderNoImage(binding)
            }

            WITH_IMAGE -> {
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

