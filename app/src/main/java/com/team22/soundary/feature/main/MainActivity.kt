package com.team22.soundary.feature.main

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.team22.soundary.R
import com.team22.soundary.databinding.ActivityMainBinding
import com.team22.soundary.feature.main.data.ShareRepositoryImpl
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setupUI()
        observeUiState()
    }

    private fun setupUI() {
        binding.likeButton.setOnClickListener {
            ShareRepositoryImpl().add()
        }
        binding.sortSpinner.adapter = ArrayAdapter(this,R.layout.main_spinner_item,viewModel.friendNames)
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
                binding.musicNameTextView.text = uiState.musicName
                binding.singerTextView.text = uiState.singer
                binding.messageTextView.text = uiState.message
                binding.nextImageView.visibility = if (uiState.isLastSong) GONE else VISIBLE
                binding.prevImageView.visibility = if (uiState.isFirstSong) GONE else VISIBLE
            }
        }

        lifecycleScope.launch {
            viewModel.uiState.collect { uiState ->
            }
        }

    }

}