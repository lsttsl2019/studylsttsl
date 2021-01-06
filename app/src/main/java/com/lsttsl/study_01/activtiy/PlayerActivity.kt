package com.lsttsl.study_01.activtiy

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.lsttsl.study_01.R
import com.lsttsl.study_01.activtiy.player.NavSecondPlayer
import com.lsttsl.study_01.databinding.ActivityPlayerBinding
import com.lsttsl.study_01.recycelrViewItem.adapter.PlayAdapter
import com.lsttsl.study_01.recycelrViewItem.item.PlayerItem

class PlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayerBinding
    private lateinit var navPlayer: NavSecondPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        baseViewSetting()
        createReView()
        setClickEvent()

    }


    private var isProgress: Boolean = false
        set(value) {

            if (value) {
                binding.playBottomNav.playerProgress.visibility = View.VISIBLE
            } else {
                binding.playBottomNav.playerProgress.visibility = View.GONE
            }
            field = value
        }

    private fun baseViewSetting() {
        isProgress = false
        binding.playBottomNav.playerTitle.text = "플레이어를 시작해주세요......"
        binding.playBottomNav.playerTitle.isSelected = true


    }


    private fun createPlayerList(): ArrayList<PlayerItem> {
        val playInfoList = ArrayList<PlayerItem>()
        val songUrl = "android.resource://${applicationContext.packageName}/" + R.raw.sample
        val songUrl2 = "android.resource://${applicationContext.packageName}/" + R.raw.sample02
        val songUrl3 = "android.resource://${applicationContext.packageName}/" + R.raw.sample03

        playInfoList.add(PlayerItem("악역", "스윙스, 이하이, 사이먼 도미닉", R.drawable.img04, songUrl, true))
        playInfoList.add(PlayerItem("VVS", "머쉬베놈, 미란이 ", R.drawable.img02, songUrl2, true))
        playInfoList.add(PlayerItem("그거 아세요??", "과나", R.drawable.img01, songUrl3, false))
        return playInfoList
    }

    private var playAdapter: PlayAdapter? = null
    private fun createReView() {

        playAdapter = PlayAdapter(createPlayerList())


        binding.playViewpager.adapter = playAdapter
        binding.playViewpager.clipToPadding = false
        binding.playViewpager.clipChildren = false
        val dpValue = 54
        val d = resources.displayMetrics.density
        val margin = (dpValue * d).toInt()

        binding.playViewpager.setPadding(margin, 0, margin, 0)
        binding.playViewpager.setPageTransformer(MarginPageTransformer(margin / 2))


        binding.playViewpager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                val playInfo = playAdapter?.getPagePlayInfo(position)
                if (playInfo != null) {
                    navPlayer = NavSecondPlayer(binding, applicationContext)
                    navPlayer.changeNav(playInfo)
                    navPlayer.initializePlayer(playInfo.songUrl)

                }
            }

            override fun onPageScrollStateChanged(state: Int) {
                navPlayer.releasePlayer()

            }

        })

    }


    private fun setClickEvent() {
        binding.playBottomNav.playerPause.setOnClickListener(onClickListener)
        binding.playBottomNav.playerPlaying.setOnClickListener(onClickListener)

    }

    private val onClickListener = View.OnClickListener { view ->
        when (view) {
            binding.playBottomNav.playerPause -> {
                navPlayer.pasue()
                playAdapter!!.isPausePlaying = false

            }
            binding.playBottomNav.playerPlaying -> {
                navPlayer.playing()
                playAdapter!!.isPausePlaying = true

            }


        }
    }


    companion object {
        private val TAG = PlayerActivity::class.simpleName

        private const val PLAY_TAG = "Player"

    }

}