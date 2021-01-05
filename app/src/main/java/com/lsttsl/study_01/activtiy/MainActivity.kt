package com.lsttsl.study_01.activtiy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.TranslateAnimation
import android.widget.PopupMenu
import androidx.constraintlayout.widget.ConstraintLayout

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.lsttsl.study_01.R
import com.lsttsl.study_01.activtiy.Nav.NavEvent
import com.lsttsl.study_01.activtiy.player.NavPlayer
import com.lsttsl.study_01.databinding.ActivityMainBinding
import com.lsttsl.study_01.fragment.*
import com.lsttsl.study_01.recycelrViewItem.item.HomeItem
import com.lsttsl.study_01.recycelrViewItem.item.TodoItem
import com.lsttsl.study_01.util.dpToPx
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navEvent: NavEvent
    private var isPopupCheck = false
    private var exoPlayer: NavPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        baseFragment()
        createToolBar()
        onBtnEvent()

    }

    // todofragment 각각 마다 플레이가 들어가기 때문에 체크 기
    var isTodoTagChange = false

    private var isPopupVisibility: Boolean = false
        set(value) {
            if (value) binding.homePopUp.visibility =
                View.VISIBLE else binding.homePopUp.visibility = View.GONE
            field = value
        }


    private fun onBtnEvent() {

        binding.homeLayout.setOnClickListener(onClickListener)
        binding.todoLayout.setOnClickListener(onClickListener)
        binding.mapLayout.setOnClickListener(onClickListener)
        binding.messageLayout.setOnClickListener(onClickListener)
        binding.settingLayout.setOnClickListener(onClickListener)
        binding.homePopUp.setOnClickListener(onClickListener)
        binding.toolbarBack.setOnClickListener(onClickListener)
        binding.todoPlayer.playerClose.setOnClickListener(onClickListener)

        binding.todoPlayer.playerPlaying.setOnClickListener(onClickListener)
        binding.todoPlayer.playerPause.setOnClickListener(onClickListener)

        binding.todoPlayer.playerTitle.isSelected = true


        binding.todoPlayer.playerList.setOnClickListener(onClickListener)

    }


    private fun createDayTime(): String {

        val now: Long = System.currentTimeMillis()
// 현재 시간을 Date 타입으로 변환
        val date = Date(now)
// 날짜, 시간을 가져오고 싶은 형태 선언
        val dateFormat = SimpleDateFormat("yyyy/MM/dd - a HH:mm", Locale("ko", "KR"))
// 현재 시간을 dateFormat 에 선언한 형태의 String 으로 변환
        return dateFormat.format(date)
    }

    private fun todoDataList(): ArrayList<TodoItem> {
        val todoInfoList = ArrayList<TodoItem>()
        val stringTime = createDayTime()
        todoInfoList.add(TodoItem("남이강", stringTime, R.drawable.img02))
        todoInfoList.add(TodoItem("한강", stringTime, R.drawable.img01))
        todoInfoList.add(TodoItem("뚝섬", stringTime, R.drawable.img03))
        todoInfoList.add(TodoItem("남산", stringTime, R.drawable.img04))
        todoInfoList.add(TodoItem("관악산", stringTime, R.drawable.img01))
        todoInfoList.add(TodoItem("북한산", stringTime, R.drawable.img02))
        todoInfoList.add(TodoItem("남해", stringTime, R.drawable.img03))
        todoInfoList.add(TodoItem("이순신박물관", stringTime, R.drawable.img04))
        todoInfoList.add(TodoItem("국립미술관", stringTime, R.drawable.img01))

        return todoInfoList
    }

    private fun homeDataList(): ArrayList<HomeItem> {

        val homeInfoList = ArrayList<HomeItem>()
        val stringTime = createDayTime()

        homeInfoList.add(HomeItem("홍천", stringTime, R.drawable.img01))
        homeInfoList.add(HomeItem("남이섬", stringTime, R.drawable.img02))
        homeInfoList.add(HomeItem("여수", stringTime, R.drawable.img03))
        homeInfoList.add(HomeItem("전주", stringTime, R.drawable.img04))
        homeInfoList.add(HomeItem("남해", stringTime, R.drawable.img03))

        return homeInfoList
    }

    private val onClickListener = View.OnClickListener { view ->
        when (view) {
            binding.homeLayout -> {
                homeFragmentCreate()
            }

            binding.todoLayout -> {
                isPopupVisibility = false
                navEvent.isNavTodoEvent = true
                val fragment = TodoFragment.instance()
                val bundle = Bundle()
                bundle.putParcelableArrayList("todoData", todoDataList())
                binding.toolbarTitle.text = "상세투여 내역"
                binding.homePopUp.visibility = View.VISIBLE
                fragment.arguments = bundle

                isTodoTagChange = true

                createFragment(fragment, TODO_TAG)
            }

            binding.mapLayout -> {
                isPopupVisibility = false
                navEvent.isNavMapEvent = true
                val fragment = MapFragment.instance()
                createFragment(fragment, MAP_TAG)

            }
            binding.messageLayout -> {
                isPopupVisibility = false
                navEvent.isNavMessageEvent = true
                val fragment = MessageFragment.instance()
                createFragment(fragment, MESSAGE_TAG)
            }

            binding.settingLayout -> {
                isPopupVisibility = false
                navEvent.isNavSettingEvent = true
                val fragment = SettingFragment.instance()
                createFragment(fragment, SETTING_TAG)
            }

            binding.homePopUp -> {
                val popup = PopupMenu(this, view, Gravity.CENTER)
                val inflater = popup.menuInflater
                val menu = popup.menu
                inflater.inflate(R.menu.menu_toolbar, menu)
                popup.show()
                popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.top_new -> {
                            //  최신 업로드된.. or 데이터상 날짜순
                            Log.d(TAG, "onOptionsItemSelected: new")
                            if (isPopupCheck) {
                                (supportFragmentManager.findFragmentByTag(HOME_TAG) as HomeFragment).refreshHomeFragment(
                                    isPopupCheck
                                )
                                isPopupCheck = false
                            }
                        }
                        R.id.top_old -> {
                            // 가장 오래 업로드 .. 데이터상 날짜순..
                            Log.d(TAG, "onOptionsItemSelected: old")
                            if (!isPopupCheck) {
                                (supportFragmentManager.findFragmentByTag(HOME_TAG) as HomeFragment).refreshHomeFragment(
                                    isPopupCheck
                                )
                                isPopupCheck = true
                            }


                        }
                    }
                    return@OnMenuItemClickListener false
                })

            }

            binding.toolbarBack -> {
                if (binding.homeLayout.visibility == View.VISIBLE) {
                    homeFragmentCreate()
                } else {
                    finishAffinity()
                }

            }

            binding.todoPlayer.playerClose -> {
                if (isTodoTagChange) {
                    todoItemOnClickAnimation(false)
                } else {
                    (supportFragmentManager.findFragmentByTag(TODO_DETAIL_TAG) as TodoDetailFragment).isPause()
                }
            }

            binding.todoPlayer.playerPlaying -> {

                exoPlayer?.playing()
            }

            binding.todoPlayer.playerPause -> {
                exoPlayer?.pause()
            }

            binding.todoPlayer.playerList -> {
                if (isTodoTagChange) {
                    val fragment = TodoDetailFragment.instance()
                    todoItemOnClickAnimation(false)
                    binding.toolbar.visibility = View.GONE
                    isTodoTagChange = false
                    createFragment(fragment, TODO_DETAIL_TAG)
                } else {
                    (supportFragmentManager.findFragmentByTag(TODO_DETAIL_TAG) as TodoDetailFragment).goToPlayer()
                }

            }

        }
    }

    private fun homeFragmentCreate() {
        isPopupVisibility = true
        val fragment = HomeFragment.instance()
        val homeListInfo: ArrayList<HomeItem> = homeDataList()
        val bundle = Bundle()
        binding.toolbarTitle.text = "나의 여행기"
        navEvent.isNavHomeEvent = true
        bundle.putParcelableArrayList("homeData", homeListInfo)
        fragment.arguments = bundle
        createFragment(fragment, HOME_TAG)
    }


    private fun createToolBar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        binding.toolbarTitle.text = "나의 여행기"
    }


    private fun baseFragment() {
        navEvent = NavEvent(binding)
        val fragment = HomeFragment.instance()

        navEvent.isNavHomeEvent = true
        val homeListInfo: ArrayList<HomeItem> = homeDataList()
        val bundle = Bundle()
        isPopupVisibility = true
        bundle.putParcelableArrayList("homeData", homeListInfo)
        fragment.arguments = bundle

        createFragment(fragment, HOME_TAG)

    }

    private fun createFragment(fragment: Fragment, tag: String) {
        binding.baseFragment.removeAllViewsInLayout()
        supportFragmentManager.beginTransaction().replace(binding.baseFragment.id, fragment, tag)
            .commitAllowingStateLoss()

    }


    fun changeFragment(fragment: Fragment) {
        binding.toolbarTitle.text = "상세투어 내역"
        binding.homePopUp.visibility = View.GONE
        navEvent.isNavTodoEvent = true
        isTodoTagChange = true
        createFragment(fragment, TODO_TAG)
    }

    fun getTodoItem(): ArrayList<TodoItem> {
        return todoDataList()
    }


    private fun aniSetCreate(): AnimationSet {
        val aniSet = AnimationSet(true)
        aniSet.interpolator = AccelerateInterpolator()
        val transInAni = TranslateAnimation(0f, 0f, 100.0f, 0f)
        transInAni.duration = 500
        aniSet.addAnimation(transInAni)
        return aniSet
    }

    private fun aniOutCreate(): AnimationSet {
        val aniOut = AnimationSet(true)
        val transOutAni = TranslateAnimation(0f, 0f, 0f, 300.0f);
        transOutAni.duration = 500
        aniOut.addAnimation(transOutAni)
        return aniOut
    }

    fun todoItemOnClickAnimation(check: Boolean) {
        val todoBinding =
            (supportFragmentManager.findFragmentByTag(TODO_TAG) as TodoFragment).returnTodoBinding()
        val layoutLP = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT
        )
        if (check) {
            if (binding.todoPlayerLayout.visibility == View.GONE) {
                val aniSet = aniSetCreate()
                aniSet.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(p0: Animation?) {
                    }

                    override fun onAnimationEnd(p0: Animation?) {
                        layoutLP.bottomMargin = dpToPx(100)
                        todoBinding?.todoRecyclerview?.layoutParams = layoutLP
                    }

                    override fun onAnimationRepeat(p0: Animation?) {
                    }
                })
                binding.todoPlayerLayout.animation = aniSet
                binding.todoPlayerLayout.visibility = View.VISIBLE
                exoPlayer = NavPlayer(binding, applicationContext)
                exoPlayer?.initializePlayer()
            }
        } else {
            if (binding.todoPlayerLayout.visibility == View.VISIBLE) {
                val aniOut = aniOutCreate()
                aniOut.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(p0: Animation?) {
                        layoutLP.bottomMargin = dpToPx(0)
                        todoBinding?.todoRecyclerview?.layoutParams = layoutLP
                    }

                    override fun onAnimationEnd(p0: Animation?) {

                    }

                    override fun onAnimationRepeat(p0: Animation?) {
                    }
                })

                binding.todoPlayerLayout.animation = aniOut
                binding.todoPlayerLayout.visibility = View.GONE
                exoPlayer?.releasePlayer()
                exoPlayer = null
            }
        }
    }


    fun todoDetailFragmentOnClickAnimation(check: Boolean) {
        if (check) {
            val aniSet = aniSetCreate()
            binding.todoPlayerLayout.animation = aniSet
            binding.todoPlayerLayout.visibility = View.VISIBLE
            exoPlayer = NavPlayer(binding, applicationContext)
            exoPlayer?.initializePlayer()
        } else {
            //  (supportFragmentManager.findFragmentByTag(TODO_DETAIL_TAG) as TodoDetailFragment).
            val aniOut = aniOutCreate()
            binding.todoPlayerLayout.animation = aniOut
            binding.todoPlayerLayout.visibility = View.GONE
            exoPlayer?.releasePlayer()
            exoPlayer = null
        }


    }


    companion object {
        private val TAG = MainActivity::class.simpleName

        private const val HOME_TAG = "Home"
        private const val TODO_TAG = "Todo"
        private const val MAP_TAG = "MAP"
        private const val MESSAGE_TAG = "MESSAGE"
        private const val SETTING_TAG = "SETTING"


        private const val TODO_DETAIL_TAG = "TodoDetail"
    }


}