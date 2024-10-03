package com.team22.soundary.feature.share

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.team22.soundary.R
import com.team22.soundary.databinding.BottomSheetBinding
import com.team22.soundary.feature.share.data.FriendItemEntity

class ShareBottomSheet : BottomSheetDialogFragment(R.layout.bottom_sheet) {
    private var _binding: BottomSheetBinding? = null
    private val binding get() = _binding!!

    private lateinit var friendList : MutableList<FriendItemEntity>
    private lateinit var adapter : BottomSheetAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = BottomSheetBinding.bind(view)

        setSendButton()
        setRecyclerView(view)
    }

    private fun setSendButton() {
        binding.bottomSheetSendButton.setOnClickListener {
            dismiss()
        }
    }

    private fun setRecyclerView(view: View) {
        // recyclerView 임시 데이터 생성
        friendList = mutableListOf<FriendItemEntity>()
        for (i in 0..10) {
            friendList.add(FriendItemEntity(""+i, "쿠키즈", null, false))
        }
        for (i in 11..19) {
            friendList.add(FriendItemEntity(""+i, "쿠키즈", "imageSrc", false))
        }

        adapter = BottomSheetAdapter(friendList, object : FriendItemClickListener {
            override fun onClick(v: View, selectItem: FriendItemEntity) {
                selectItem.isSelected = !selectItem.isSelected // 데이터 수정 부분 나중에 다른 파일로 옮기기

                val updatedList = adapter.currentList.map {
                    if(it.id == selectItem.id) {
                        it.copy(isSelected = !it.isSelected)
                    } else {
                        it
                    }
                }
                adapter.submitList(updatedList)
                Log.d("uin", "" + selectItem.isSelected)
            }
        })

        binding.selectFriendRecyclerview.adapter = adapter
        binding.selectFriendRecyclerview.layoutManager = GridLayoutManager(view.context, 4)
        adapter.submitList(friendList)
    }


    companion object {
        const val TAG = "ShareBottomModalSheet"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}