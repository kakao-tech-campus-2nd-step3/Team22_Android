package com.team22.soundary.feature.main

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.lifecycle.lifecycleScope
import com.team22.soundary.R
import com.team22.soundary.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        observeUiState()
    }

    private fun setupUI() {
        setSongClickListener()
        setSpinner()
    }

    private fun setSongClickListener() {
        binding.nextImageView.setOnClickListener {
            viewModel.onNextClicked()
        }

        binding.prevImageView.setOnClickListener {
            viewModel.onPrevClicked()
        }

    }

    private fun setSpinner() {
        binding.sortSpinner.adapter =
            ArrayAdapter(this, R.layout.main_spinner_item, viewModel.friendNames)
        binding.sortSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                viewModel.onFriendChanged(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun observeUiState() {
        lifecycleScope.launch {
            viewModel.uiState.collect { uiState ->
                binding.friendNameTextView.text = uiState.friendName
                binding.musicNameTextView.text = uiState.musicName
                binding.singerTextView.text = uiState.singer
                binding.messageTextView.text = uiState.message
                binding.nextImageView.isGone = uiState.isLastSong
                binding.prevImageView.isGone= uiState.isFirstSong
                binding.likeButton.setImageResource(if(uiState.isLikeSong) R.drawable.main_like_background_pressed else R.drawable.main_like_background)
            }
        }
    }

}