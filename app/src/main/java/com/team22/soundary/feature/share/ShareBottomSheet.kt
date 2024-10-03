package com.team22.soundary.feature.share

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.team22.soundary.R
import com.team22.soundary.databinding.BottomSheetBinding
import com.team22.soundary.feature.share.data.FriendItemEntity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ShareBottomSheet : BottomSheetDialogFragment(R.layout.bottom_sheet) {
    private var _binding: BottomSheetBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: BottomSheetAdapter
    private val viewModel: ShareViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = BottomSheetBinding.bind(view)

        setSendButton()
        setRecyclerView(view)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireActivity(), R.style.bottomSheetBackground)
    }

    private fun setSendButton() {
        binding.bottomSheetSendButton.setOnClickListener {
            viewModel.updateSelectItemList()
            dismiss()
        }
    }

    private fun setRecyclerView(view: View) {
        adapter = BottomSheetAdapter(object : FriendItemClickListener {
            override fun onClick(v: View, selectItem: FriendItemEntity) {
                viewModel.setItemVisibility(selectItem)
            }
        })
        binding.selectFriendRecyclerview.adapter = adapter
        binding.selectFriendRecyclerview.layoutManager = GridLayoutManager(view.context, 4)

        lifecycleScope.launch {
            viewModel.friendList.collect {
                adapter.submitList(it)
            }
        }
    }

    companion object {
        const val TAG = "ShareBottomModalSheet"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}