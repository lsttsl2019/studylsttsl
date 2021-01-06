package com.lsttsl.study_01.activtiy.player

import android.content.Context
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.view.View
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.lsttsl.study_01.databinding.ActivityPlayerBinding
import com.lsttsl.study_01.recycelrViewItem.item.PlayerItem
import com.lsttsl.study_01.util.toTimeString

class NavSecondPlayer(
    private val binding: ActivityPlayerBinding,
    private val context: Context
) {

    private val player: SimpleExoPlayer by lazy { SimpleExoPlayer.Builder(context).build() }


    private val autoCurrentTimeHandler = Handler(Looper.getMainLooper())
    private val autoCurrentRunnable = Runnable {
        isCurrentTime = true
    }

    private var isPausePlaying: Boolean = false
        set(value) {
            if (value) {
                player.play()
                binding.playBottomNav.playerPause.visibility = View.VISIBLE
                binding.playBottomNav.playerPlaying.visibility = View.INVISIBLE
            } else {
                binding.playBottomNav.playerPause.visibility = View.INVISIBLE
                binding.playBottomNav.playerPlaying.visibility = View.VISIBLE
                player.pause()
            }

            field = value
        }

    private var isCurrentTime: Boolean = false
        set(value) {
            autoCurrentTimeHandler.removeCallbacks(autoCurrentRunnable)

            if (value) {
                var currentPosition = 0
                if (player.currentPosition.toInt() != 0) {
                    currentPosition = player.currentPosition.toInt() / 1000
                }
                binding.playBottomNav.playerLeftTime.text = currentPosition.toTimeString(true)
                isPausePlaying = true

                autoCurrentTimeHandler.postDelayed(autoCurrentRunnable, CURRENT_CHECK_TIME)

            } else {
                isPausePlaying = false
            }


            field = value
        }


    fun changeNav(playInfo: PlayerItem) {
        binding.playBottomNav.playerTitle.text = playInfo.title
        if (playInfo.movemusic) {
            binding.playerTopTitle.text = "비디오 보기"
        } else {
            binding.playerTopTitle.text = "음악 감상"
        }
    }

    fun initializePlayer(playUrl: String) {
        preparPlayer(playUrl)
        player.seekTo(0L)
        player.playWhenReady = true
        player.prepare()
        player.addListener(playerListener)
        //isCurrentTime = true

    }

    fun playing() {
        isCurrentTime = true
    }

    fun pasue() {
        isCurrentTime = false
    }


    fun releasePlayer() {
        player.removeListener(playerListener)
        isCurrentTime = false
        player.release()
    }

    private fun preparPlayer(playUrl: String) {

        val mediaSource = buildMediaSource(playUrl)
        player.setMediaSource(mediaSource)

    }


    private fun buildMediaSource(playUrl: String): MediaSource {
        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(MediaItem.fromUri(Uri.parse(playUrl)))
    }

    private val dataSourceFactory: DataSource.Factory by lazy {
        DefaultDataSourceFactory(
            context,
            "media"
        )
    }


    private val playerListener = object : Player.EventListener {

        override fun onPlaybackStateChanged(state: Int) {
            when (state) {
                Player.STATE_READY -> {
                    var duration = 0
                    if (player.duration.toInt() != 0) {
                        duration = player.duration.toInt() / 1000
                    }
                    binding.playBottomNav.playerRightTime.text = duration.toTimeString(true)
                    binding.playBottomNav.playerProgress.visibility = View.GONE
                  //  isCurrentTime = true

                }

                Player.STATE_ENDED ->{
                    pasue()
                    player.seekTo(0L)
                }

            }


        }


    }

    companion object {
        private const val CURRENT_CHECK_TIME = 1000L
        private val TAG = NavSecondPlayer::class.simpleName
    }
}