package com.team22.soundary.feature.share

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import androidx.core.widget.addTextChangedListener
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

    private var lastCheckedRadioButtonId: Int? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = BottomSheetBinding.bind(view)

        setSendButton()
        setComment()
        setRecyclerView(view)
        setSelectAllButton()
        setCategoryRadioButton()
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
            override fun onClick(v: View, selectItem: FriendItemEntity) {
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
    
    private fun setCategoryRadioButton() {
        binding.categoryRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            if(checkedId != -1) {
                val selectedRadioButton = group.findViewById<RadioButton>(checkedId)
                selectedRadioButton.setOnClickListener {
                    if (lastCheckedRadioButtonId == checkedId) { // 이미 선택된 RadioButton을 다시 클릭한 경우
                        binding.categoryRadioGroup.clearCheck()
                        lastCheckedRadioButtonId = null
                        Log.d("uin", "전체선택해제")
                    } else {
                        lastCheckedRadioButtonId = checkedId
                        Log.d("uin", "" + checkedId + "선택")
                    }
                }
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