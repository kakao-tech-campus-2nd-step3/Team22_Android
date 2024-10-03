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
        val friendList = mutableListOf<FriendItemEntity>()
        for (i in 0..5) {
            friendList.add(FriendItemEntity(""+i, "쿠키즈", null))
            friendList.add(FriendItemEntity(""+i, "쿠키즈", "imageSrc"))
        }

        binding.selectFriendRecyclerview.adapter = BottomSheetAdapter(friendList, object : FriendItemClickListener {
            override fun onClick(v: View, selectItem: FriendItemEntity) {
                Log.d("uin", "" + selectItem.id)
            }
        })
        binding.selectFriendRecyclerview.layoutManager = GridLayoutManager(view.context, 4)
    }


    companion object {
        const val TAG = "ShareBottomModalSheet"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}