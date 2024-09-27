package com.team22.soundary.feature.search

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.HorizontalScrollView
import android.widget.TextView
import com.team22.soundary.R
import com.team22.soundary.feature.search.data.Friend

class PendingFriendAdapter(
    private val groupTitle: String, // "대기중인 친구"
    private val pendingFriendsList: List<Friend>
) : BaseExpandableListAdapter() {

    override fun getGroupCount(): Int {
        return 1 // 그룹은 하나만
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return pendingFriendsList.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return groupTitle
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return pendingFriendsList[childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val groupTitleView: TextView = convertView?.findViewById(android.R.id.text1)
            ?: LayoutInflater.from(parent?.context)
                .inflate(android.R.layout.simple_expandable_list_item_1, parent, false) as TextView
        groupTitleView.text = groupTitle
        return groupTitleView
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val friend = getChild(groupPosition, childPosition) as Friend
        val view = convertView ?: LayoutInflater.from(parent?.context)
            .inflate(R.layout.friend_item_pending, parent, false)

        // 여기에서 발생하는 NullPointerException을 방지하기 위한 수정
        val nameTextView = view.findViewById<TextView>(R.id.user_name_textview)
        val genreTextView = view.findViewById<TextView>(R.id.favorite_genre_textview)

        // NullPointerException 방지: view 요소가 null이 아닌지 확인
        if (nameTextView != null && genreTextView != null) {
            nameTextView.text = friend.name
            genreTextView.text = friend.genre
        } else {
            // 로그를 추가하여 문제를 디버깅할 수 있습니다.
            Log.e("PendingFriendAdapter", "TextView ID가 null입니다. 레이아웃 파일을 확인하세요.")
        }

        return view
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}
