package com.team22.soundary.feature.main

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.view.isGone
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.team22.soundary.R
import com.team22.soundary.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.IOException

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val player: MediaPlayer = MediaPlayer()
    private var shouldPreparePlayer: Boolean = true
    private var pausedPosition: Int = 0
    private val MediaPlayer.isPaused: Boolean
        get() = !player.isPlaying && player.currentPosition != 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        observeUiState()
        setupUI()
    }

    private fun setupUI() {
        setSongClickListener()
        setSpinner()
        setMediaPlayer()
    }

    private fun setSongClickListener() {
        binding.nextImageView.setOnClickListener {
            resetMediaPlayer()
            resetText()
            viewModel.onNextClicked()
        }

        binding.prevImageView.setOnClickListener {
            resetMediaPlayer()
            resetText()
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
                resetMediaPlayer()
                resetText()
                viewModel.onFriendChanged(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun setMediaPlayer() {
        player.setOnSeekCompleteListener {
            it.start()
            updateMusicState()
        }

        player.setOnCompletionListener {
            binding.mainProgressBar.progress = 0
            pausedPosition = 0
        }

        player.setOnPreparedListener {
            binding.mainProgressBar.max = player.duration / PROGRESS_UPDATE_DELAY
            it.start()
            updateMusicState()
            shouldPreparePlayer = false
        }

        binding.currentImageView.setOnClickListener {
            if (shouldPreparePlayer) {
                prepareMediaPlayer()
            } else {
                if (player.isPaused) {
                    player.seekTo(pausedPosition)
                } else {
                    player.pause()
                    pausedPosition = player.currentPosition
                }
            }
        }
    }

    private fun resetText() {
        binding.messageTextView.visibility = View.INVISIBLE
        binding.messageTailImageView.visibility = View.INVISIBLE
        binding.instructionTextView.text = getString(
            R.string.main_not_enough_listen,
            MESSAGE_THRESHOLD
        )
    }

    private fun resetMediaPlayer() {
        player.reset()
        pausedPosition = 0
        binding.mainProgressBar.progress = 0
        shouldPreparePlayer = true
    }

    private fun updateMusicState() {
        lifecycleScope.launch {
            while(true){
                if (player.isPlaying){
                    updateProgressBar()
                    updateText()
                }
                delay(PROGRESS_UPDATE_DELAY.toLong())
            }
        }
    }

    private fun updateProgressBar() {
        val progressValue = player.currentPosition / PROGRESS_UPDATE_DELAY
        binding.mainProgressBar.progress = progressValue
    }

    private fun updateText() {
        val currentSecond = player.currentPosition / 1000
        checkMessageVisibility(currentSecond)
        controlInstructionText(currentSecond)
    }

    private fun checkMessageVisibility(currentSecond: Int) {
        if (currentSecond >= MESSAGE_THRESHOLD) {
            binding.messageTextView.visibility = View.VISIBLE
            binding.messageTailImageView.visibility = View.VISIBLE
        }
    }

    private fun controlInstructionText(currentSecond: Int) {
        val messageTimeLeft = MESSAGE_THRESHOLD - currentSecond

        binding.instructionTextView.text =
            if (messageTimeLeft > 0) getString(
                R.string.main_not_enough_listen,
                messageTimeLeft
            ) else getString(R.string.main_enough_listen)
    }

    private fun prepareMediaPlayer() {
        try {
            val uri = viewModel.getSongUri()
            if (uri != Uri.EMPTY) {
                player.setDataSource(
                    this,
                    uri
                )
                player.prepareAsync()
            }
        } catch (e: Exception) {
            when (e) {
                is IOException, is IllegalArgumentException -> {
                    Snackbar.make(this, binding.main, "에러 발생 : " + e.message, Snackbar.LENGTH_LONG)
                        .show()
                }

                else -> {}
            }
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
                binding.prevImageView.isGone = uiState.isFirstSong
                binding.likeButton.setImageResource(if (uiState.isLikeSong) R.drawable.main_like_background_pressed else R.drawable.main_like_background)
                uiState.friendImage?.let {
                    Glide.with(this@MainActivity)
                        .load(it)
                        .into(binding.friendPicImageView)
                }
                uiState.songImage?.let {
                    Glide.with(this@MainActivity)
                        .load(it)
                        .apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
                        .into(binding.currentImageView)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }

    companion object {
        const val PROGRESS_UPDATE_DELAY = 50
        const val MESSAGE_THRESHOLD = 5
    }
}