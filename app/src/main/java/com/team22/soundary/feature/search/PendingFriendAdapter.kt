package com.team22.soundary.feature.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.team22.soundary.R
import com.team22.soundary.feature.search.data.Friend

class PendingFriendAdapter(
    private val pendingFriendsList: List<Friend> // 대기중인 친구 목록
) : RecyclerView.Adapter<PendingFriendAdapter.PendingFriendViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendingFriendViewHolder {
        // friend_item_pending.xml을 inflate하여 ViewHolder 생성
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.friend_item_pending, parent, false)
        return PendingFriendViewHolder(view)
    }

    override fun onBindViewHolder(holder: PendingFriendViewHolder, position: Int) {
        val friend = pendingFriendsList[position]
        holder.bind(friend) // 친구 데이터를 ViewHolder에 바인딩
    }

    override fun getItemCount(): Int {
        return pendingFriendsList.size
    }

    // ViewHolder 정의
    class PendingFriendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val profileInitialTextView: TextView = itemView.findViewById(R.id.profile_initial_textview)
        private val userNameTextView: TextView = itemView.findViewById(R.id.user_name_textview)

        // 데이터를 바인딩하는 메서드
        fun bind(friend: Friend) {
            // 프로필 이니셜과 이름 설정
            profileInitialTextView.text = friend.name.first().toString() // 이름 첫 글자를 가져와서 설정
            userNameTextView.text = friend.name
        }
    }
}
