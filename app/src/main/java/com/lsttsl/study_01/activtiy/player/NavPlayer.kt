package com.lsttsl.study_01.activtiy.player

import android.content.Context
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.util.Log

import android.view.View
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.TrackSelection
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.lsttsl.study_01.R
import com.lsttsl.study_01.databinding.ActivityMainBinding
import com.lsttsl.study_01.util.toTimeString
import java.io.File

class NavPlayer(private val binding: ActivityMainBinding, private val context: Context) {

    private val player: SimpleExoPlayer by lazy { SimpleExoPlayer.Builder(context).build() }

    private val songUrl = Uri.parse("android.resource://${context.packageName}/" + R.raw.sample)


    private val autoCurrentTimeHandler = Handler(Looper.getMainLooper())
    private val autoCurrentRunnable = Runnable {
        isCurrentTime = true
    }
    private var isCurrentTime: Boolean = false
        set(value) {
            autoCurrentTimeHandler.removeCallbacks(autoCurrentRunnable)

            if (value) {
                var currentPosition = 0
                if (player.currentPosition.toInt() != 0) {
                    currentPosition = player.currentPosition.toInt() / 1000
                }
                binding.todoPlayer.playerLeftTime.text = currentPosition.toTimeString(true)
                binding.todoPlayer.playerPlaying.visibility = View.INVISIBLE
                binding.todoPlayer.playerPause.visibility = View.VISIBLE
                autoCurrentTimeHandler.postDelayed(autoCurrentRunnable, CURRENT_CHECK_TIME)

            }else{
                binding.todoPlayer.playerPlaying.visibility = View.VISIBLE
                binding.todoPlayer.playerPause.visibility = View.INVISIBLE
            }


            field = value
        }


    fun initializePlayer() {
        preparePlayer()
        binding.todoPlayer.playerTitle.text = "악역 - 스윙스,이하이,사이먼도미닉"
        player.seekTo(0L)
        player.playWhenReady = true
        player.prepare()
        player.addListener(playerListener)
        player.play()
        isCurrentTime = true
    }

    fun playing() {
        player.play()

        isCurrentTime = true
    }

    fun pause() {
        player.pause()
        isCurrentTime = false

    }

    fun releasePlayer() {
        player.removeListener(playerListener)
        isCurrentTime = false
        player.release()

    }

    private val playerListener = object : Player.EventListener {

        override fun onIsPlayingChanged(isPlaying: Boolean) {
            if (isPlaying) {
                isCurrentTime = true
            }
        }

        override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
            if (playbackState == Player.STATE_BUFFERING) {
                binding.todoPlayer.playerProgress.visibility = View.VISIBLE
            } else {
                binding.todoPlayer.playerProgress.visibility = View.GONE
            }

        }

        override fun onPlaybackStateChanged(state: Int) {

            when (state) {
                Player.STATE_READY -> {
                    val tvPlayer = player
                    var duration = 0
                    if (tvPlayer.duration.toInt() != 0) {
                        duration = tvPlayer.duration.toInt() / 1000
                    }
                    binding.todoPlayer.playerRightTime.text = duration.toTimeString(true)
                    binding.todoPlayer.playerProgress.visibility = View.GONE
                    binding.todoPlayer.playerPause.visibility = View.VISIBLE
                    binding.todoPlayer.playerPlaying.visibility = View.INVISIBLE
                }
                Player.STATE_ENDED -> {
                    pause()
                    player.seekTo(0L)

                }

            }


        }


    }


    private val dataSourceFactory: DataSource.Factory by lazy {
        DefaultDataSourceFactory(
            context,
            "sample"
        )
    }

    private fun buildMediaSource(): MediaSource {
        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(MediaItem.fromUri(songUrl))
    }

    private fun preparePlayer() {
        val mediaSource = buildMediaSource()
        player.setMediaSource(mediaSource)
    }


    companion object {
        private const val CURRENT_CHECK_TIME = 1000L
        private val TAG = NavPlayer::class.simpleName
    }
}