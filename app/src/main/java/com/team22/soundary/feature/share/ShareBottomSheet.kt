package com.team22.soundary.feature.share

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.team22.soundary.R
import com.team22.soundary.core.domain.model.Category
import com.team22.soundary.core.domain.model.User
import com.team22.soundary.databinding.BottomSheetBinding
import kotlinx.coroutines.flow.collectLatest
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
        observeSelectedFriends()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireActivity(), R.style.bottomSheetBackground)
    }

    private fun setSendButton() {
        updateSendButtonText()
        binding.bottomSheetSendButton.setOnClickListener {
            viewModel.setComment(binding.shareCommentEdittext.text.toString())
            viewModel.getFilteredFriendList(null)
            dismiss()
        }
    }

    private fun setComment() {
        binding.shareCommentEdittext.setText(viewModel.comment.value)
    }

    private fun setRecyclerView(view: View) {
        adapter = BottomSheetAdapter(object : FriendItemClickListener {
            override fun onClick(v: View, selectItem: User) {
                viewModel.toggleFriendSelection(selectItem.id)
            }
        })
        binding.selectFriendRecyclerview.adapter = adapter
        binding.selectFriendRecyclerview.layoutManager = GridLayoutManager(view.context, 4)

        lifecycleScope.launch {
            viewModel.filteredUserList.collect { friends ->
                adapter.submitList(friends)
            }
        }
    }

    private fun setSelectAllButton() {
        binding.shareSelectAllButton.setOnClickListener {
            viewModel.setAllFriendsSelected(binding.shareSelectAllButton.isChecked)
        }
    }

    private fun setCategoryRadioButton() {
        binding.categoryRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId != -1) {
                val selectedRadioButton = group.findViewById<RadioButton>(checkedId)
                selectedRadioButton.setOnClickListener {
                    if (lastCheckedRadioButtonId == checkedId) {
                        binding.categoryRadioGroup.clearCheck()
                        lastCheckedRadioButtonId = null
                        viewModel.getFilteredFriendList(null)
                    } else {
                        lastCheckedRadioButtonId = checkedId
                        val category = when (checkedId) {
                            R.id.category_rnb -> Category.RnB
                            R.id.category_hiphop -> Category.Hiphop
                            R.id.category_pop -> Category.Pop
                            R.id.category_rock -> Category.Rock
                            R.id.category_jpop -> Category.Jpop
                            R.id.category_dance -> Category.Dance
                            else -> null
                        }
                        viewModel.getFilteredFriendList(category)
                    }
                }
            }
        }
    }

    private fun observeSelectedFriends() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.selectedFriendIds.collectLatest { selectedIds ->
                updateSendButtonText()
                binding.shareSelectAllButton.isChecked = viewModel.isAllFriendsSelected()
                adapter.setSelectedIds(selectedIds)
            }
        }
    }

    private fun updateSendButtonText() {
        binding.bottomSheetSendButton.text = viewModel.getButtonText()
    }

    companion object {
        const val TAG = "ShareBottomModalSheet"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}