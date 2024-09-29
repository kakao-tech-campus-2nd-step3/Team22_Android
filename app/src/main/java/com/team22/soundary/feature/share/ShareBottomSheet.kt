package com.team22.soundary.feature.share

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.team22.soundary.R
import com.team22.soundary.feature.share.data.FriendItemEntity

class ShareBottomSheet : BottomSheetDialogFragment(R.layout.bottom_sheet) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<TextView>(R.id.bottom_sheet_send_button)

        button.setOnClickListener {
            dismiss()
        }

        // recyclerView 임시데이터 생성
        val friendList = mutableListOf<FriendItemEntity>()
        for (i in 0..10) {
            friendList.add(FriendItemEntity("이름"))
        }

        // recyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.select_friend_recyclerview)
        recyclerView.adapter = BottomSheetAdapter(friendList)
        recyclerView.layoutManager = GridLayoutManager(view.context, 4)
    }

    companion object {
        const val TAG = "ShareBottomModalSheet"
    }

}