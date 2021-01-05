package com.lsttsl.study_01.activtiy

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.MarginPageTransformer
import com.lsttsl.study_01.R
import com.lsttsl.study_01.databinding.ActivityPlayerBinding
import com.lsttsl.study_01.recycelrViewItem.adapter.PlayAdapter
import com.lsttsl.study_01.recycelrViewItem.item.PlayerItem

class PlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        baseViewSetting()
        createReView()

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
        playInfoList.add(PlayerItem("악역", "스윙스, 이하이, 사이먼 도미닉", R.drawable.img04))
        playInfoList.add(PlayerItem("VVS", "머쉬베놈, 미란이 ", R.drawable.img02))
        playInfoList.add(PlayerItem("바람기억", "나얼", R.drawable.img03))
        playInfoList.add(PlayerItem("사쿠란보", "달마발", R.drawable.img01))
        return playInfoList
    }


    private fun createReView() {

        val playAdapter = PlayAdapter(createPlayerList())


        binding.playViewpager.adapter = playAdapter
        binding.playViewpager.clipToPadding = false
        binding.playViewpager.clipChildren = false
        val dpValue = 54
        val d = resources.displayMetrics.density
        val margin = (dpValue * d).toInt()

        binding.playViewpager.setPadding(margin, 0, margin, 0)
        binding.playViewpager.setPageTransformer(MarginPageTransformer(margin / 2))

    }


    companion object {
        private val TAG = PlayerActivity::class.simpleName

        private const val PLAY_TAG = "Player"

    }

}