package com.team22.soundary.feature.share

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.team22.soundary.R
import com.team22.soundary.databinding.BottomSheetBinding
import com.team22.soundary.feature.share.domain.Friend
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
        setComment()
        setRecyclerView(view)
        setSelectAllButton()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireActivity(), R.style.bottomSheetBackground)
    }

    private fun setSendButton() {  // 메인버전이랑 공유버전이랑 나눠야
        binding.bottomSheetSendButton.text = viewModel.getButtonText()
        binding.bottomSheetSendButton.setOnClickListener {
            viewModel.updateSelectItemList()
            viewModel.setComment(binding.shareCommentEdittext.text.toString())
            dismiss()
        }
    }

    private fun setComment() {
        binding.shareCommentEdittext.setText(viewModel.comment.value)
    }

    private fun setRecyclerView(view: View) {
        adapter = BottomSheetAdapter(object : FriendItemClickListener {
            override fun onClick(v: View, selectItem: Friend) {
                viewModel.setItemVisibility(selectItem)
                binding.shareSelectAllButton.isChecked = viewModel.isSelectedAll()
                binding.bottomSheetSendButton.text = viewModel.getButtonText()
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

    private fun setSelectAllButton() {
        binding.shareSelectAllButton.isChecked = viewModel.isSelectedAll()
        binding.shareSelectAllButton.setOnClickListener {
            if (binding.shareSelectAllButton.isChecked) {
                viewModel.setItemVisibilityAll(true)
            } else {
                viewModel.setItemVisibilityAll(false)
            }
            binding.bottomSheetSendButton.text = viewModel.getButtonText()
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