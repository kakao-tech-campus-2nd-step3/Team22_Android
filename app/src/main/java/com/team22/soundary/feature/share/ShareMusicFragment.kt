package com.team22.soundary.feature.share

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.team22.soundary.R
import com.team22.soundary.databinding.FragmentShareMusicBinding
import com.team22.soundary.feature.main.MainViewModel
import com.team22.soundary.feature.share.domain.Music
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ShareMusicFragment : Fragment() {
    private var _binding: FragmentShareMusicBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: MusicListAdapter
    private val viewModel: MusicViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentShareMusicBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSpinner()
        setRecyclerView()
    }

    private fun setSpinner() {
        binding.shareSortSpinner.adapter = ArrayAdapter(
            requireContext(),
            R.layout.share_spinner_item,
            resources.getStringArray(R.array.share_sort_array)
        )
    }

    private fun setRecyclerView() {
        adapter = MusicListAdapter(object : MusicItemClickListener {
            override fun onClick(v: View, selectItem: Music) {
                val intent = Intent(requireContext(), ShareFriendActivity::class.java)
                intent.putExtra(ShareFriendActivity.KEY_MUSIC, selectItem.title)
                intent.putExtra(ShareFriendActivity.KEY_SINGER, selectItem.artists[0]) // 추후 수정
                startActivity(intent)
            }
        })
        binding.shareMusicRecyclerview.adapter = adapter
        binding.shareMusicRecyclerview.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        lifecycleScope.launch {
            viewModel.musicList.collect {
                adapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}