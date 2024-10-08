package com.team22.soundary.feature.main

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.lifecycle.lifecycleScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.Player.STATE_ENDED
import androidx.media3.common.Player.STATE_READY
import androidx.media3.exoplayer.ExoPlayer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.team22.soundary.R
import com.team22.soundary.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.IOException


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var player: ExoPlayer
    private var shouldPreparePlayer: Boolean = true
    private var pausedPosition: Long = 0
    private val ExoPlayer.isPaused: Boolean
        get() = !player.isPlaying && player.currentPosition != 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        observeUiState()
        setupUI()

    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
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

    private fun resetText() {
        binding.messageTextView.visibility = View.INVISIBLE
        binding.messageTailImageView.visibility = View.INVISIBLE
        binding.instructionTextView.text = getString(
            R.string.main_not_enough_listen,
            MESSAGE_THRESHOLD
        )
    }

    private fun resetMediaPlayer() {
        player.removeMediaItem(0)
        pausedPosition = 0
        binding.mainProgressBar.progress = 0
        shouldPreparePlayer = true
    }

    private fun setMediaPlayer() {
        player = ExoPlayer.Builder(this).build()
        setPlayerListener()

        binding.currentImageView.setOnClickListener {
            if (player.isPlaying) {
                player.pause()
                pausedPosition = player.currentPosition
            } else {
                if (shouldPreparePlayer) preparePlayer()
                if (player.isPaused) player.seekTo(pausedPosition)
                player.play()
                updateMusicState()
            }
        }
    }

    private fun setPlayerListener(){
        player.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                super.onPlaybackStateChanged(playbackState)
                when (playbackState) {
                    STATE_READY -> {
                        binding.mainProgressBar.max =
                            player.duration.toInt() / PROGRESS_UPDATE_DELAY
                        shouldPreparePlayer = false
                    }

                    STATE_ENDED -> {
                        binding.mainProgressBar.progress = 0
                        pausedPosition = 0L
                    }

                }
            }
        })
    }

    private fun updateMusicState() {
        lifecycleScope.launch {
            while (true) {
                if (player.isPlaying) {
                    updateProgressBar()
                    updateText()
                }
                delay(PROGRESS_UPDATE_DELAY.toLong())
            }
        }
    }

    private fun updateProgressBar() {
        val progressValue = player.currentPosition / PROGRESS_UPDATE_DELAY
        binding.mainProgressBar.progress = progressValue.toInt()
    }

    private fun updateText() {
        val currentSecond = player.currentPosition / 1000
        updateMessageVisibility(currentSecond)
        updateInstructionText(currentSecond)
    }

    private fun updateMessageVisibility(currentSecond: Long) {
        if (currentSecond >= MESSAGE_THRESHOLD) {
            binding.messageTextView.visibility = View.VISIBLE
            binding.messageTailImageView.visibility = View.VISIBLE
        }
    }

    private fun updateInstructionText(currentSecond: Long) {
        val messageTimeLeft = MESSAGE_THRESHOLD - currentSecond

        binding.instructionTextView.text =
            if (messageTimeLeft > 0) getString(
                R.string.main_not_enough_listen,
                messageTimeLeft
            ) else getString(R.string.main_enough_listen)
    }

    private fun preparePlayer() {
        try {
            val uri = viewModel.getSongUri()
            if (uri != Uri.EMPTY) {
                player.setMediaItem(
                    MediaItem.fromUri(uri)
                )
                player.prepare()
                shouldPreparePlayer = false
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
                binding.likeButton.setImageResource(uiState.likeBackground)
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

    companion object {
        const val PROGRESS_UPDATE_DELAY = 50
        const val MESSAGE_THRESHOLD = 5
    }
}