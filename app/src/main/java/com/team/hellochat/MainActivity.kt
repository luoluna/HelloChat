package com.team.hellochat

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.KeyEvent
import com.team.hellochat.app.MyApplication
import com.team.hellochat.fragment.ChatFragment
import com.team.hellochat.fragment.DiscoveryFragment
import com.team.hellochat.fragment.FriendsFragment
import com.team.hellochat.fragment.PersonalFragment
import com.team.hellochat.utils.ToastUtil
import kotlinx.android.synthetic.main.tab_bottom_bar.*
import java.util.*

/**
 * Created by Sweven on 2019/3/29.
 * Email:sweventears@Foxmail.com
 */
class MainActivity : BaseActivity() {

    var mainFragment = ChatFragment()
    var friendFragment = FriendsFragment()
    var discoveryFragment = DiscoveryFragment()
    var personalFragment = PersonalFragment()
    var fragmentManager = supportFragmentManager
    private var currentIndex = 0
    private val CURRENT_FRAGMENT = "STATE_FRAGMENT_SHOW"
    private var currentFragment = Fragment()
    private var fragments = ArrayList<Fragment>()
    private var EXIT = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        WindowUtil.setWhiteFontBar(this, resources.getColor(R.color.appColor))

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(CURRENT_FRAGMENT, 0)
            fragments.removeAll(fragments)
            fragments.add(fragmentManager.findFragmentByTag(0.toString() + "")!!)
            fragments.add(fragmentManager.findFragmentByTag(2.toString() + "")!!)
            fragments.add(fragmentManager.findFragmentByTag(3.toString() + "")!!)
            fragments.add(fragmentManager.findFragmentByTag(4.toString() + "")!!)
            restoreFragment()
        } else {//正常启动时调用
            fragments.add(mainFragment)
            fragments.add(friendFragment)
            fragments.add(discoveryFragment)
            fragments.add(personalFragment)
            showFragment()
        }
        resetTabBtn()
        ll_chat.setOnClickListener {
            currentIndex = 0
            showFragment()
            iv_chat.setImageResource(R.drawable.ic_person_light)
            iv_friend.setImageResource(R.drawable.ic_person_normal)
            iv_discovery.setImageResource(R.drawable.ic_person_normal)
            iv_personal.setImageResource(R.drawable.ic_person_normal)
            tv_chat.setTextColor(resources.getColor(R.color.appColor))
            tv_friend.setTextColor(resources.getColor(R.color.appTabNormal))
            tv_discovery.setTextColor(resources.getColor(R.color.appTabNormal))
            tv_personal.setTextColor(resources.getColor(R.color.appTabNormal))
        }

        ll_friend.setOnClickListener {
            currentIndex = 1
            showFragment()
            iv_chat.setImageResource(R.drawable.ic_person_normal)
            iv_friend.setImageResource(R.drawable.ic_person_light)
            iv_discovery.setImageResource(R.drawable.ic_person_normal)
            iv_personal.setImageResource(R.drawable.ic_person_normal)
            tv_chat.setTextColor(resources.getColor(R.color.appTabNormal))
            tv_friend.setTextColor(resources.getColor(R.color.appColor))
            tv_discovery.setTextColor(resources.getColor(R.color.appTabNormal))
            tv_personal.setTextColor(resources.getColor(R.color.appTabNormal))

        }

        ll_discovery.setOnClickListener {
            currentIndex = 2
            showFragment()
            iv_chat.setImageResource(R.drawable.ic_person_normal)
            iv_friend.setImageResource(R.drawable.ic_person_normal)
            iv_discovery.setImageResource(R.drawable.ic_person_light)
            iv_personal.setImageResource(R.drawable.ic_person_normal)
            tv_chat.setTextColor(resources.getColor(R.color.appTabNormal))
            tv_friend.setTextColor(resources.getColor(R.color.appTabNormal))
            tv_discovery.setTextColor(resources.getColor(R.color.appColor))
            tv_personal.setTextColor(resources.getColor(R.color.appTabNormal))

        }

        ll_personal.setOnClickListener {
            currentIndex = 3
            showFragment()
            iv_chat.setImageResource(R.drawable.ic_person_normal)
            iv_friend.setImageResource(R.drawable.ic_person_normal)
            iv_discovery.setImageResource(R.drawable.ic_person_normal)
            iv_personal.setImageResource(R.drawable.ic_person_light)
            tv_chat.setTextColor(resources.getColor(R.color.appTabNormal))
            tv_friend.setTextColor(resources.getColor(R.color.appTabNormal))
            tv_discovery.setTextColor(resources.getColor(R.color.appTabNormal))
            tv_personal.setTextColor(resources.getColor(R.color.appColor))

        }
    }

    private fun resetTabBtn() {
        iv_chat.setImageResource(R.drawable.ic_person_light)
        iv_friend.setImageResource(R.drawable.ic_person_normal)
        iv_discovery.setImageResource(R.drawable.ic_person_normal)
        iv_personal.setImageResource(R.drawable.ic_person_normal)
        tv_chat.setTextColor(resources.getColor(R.color.appColor))
        tv_friend.setTextColor(resources.getColor(R.color.appTabNormal))
        tv_discovery.setTextColor(resources.getColor(R.color.appTabNormal))
        tv_personal.setTextColor(resources.getColor(R.color.appTabNormal))
    }

    private fun restoreFragment() {
        val mBeginTransaction = fragmentManager.beginTransaction()
        for (i in fragments.indices) {
            if (i == currentIndex) {
                mBeginTransaction.show(fragments[i])
            } else {
                mBeginTransaction.hide(fragments[i])
            }
        }
        mBeginTransaction.commit()
        //把当前显示的fragment记录下来
        currentFragment = fragments[currentIndex]

    }

    private fun showFragment() {
        val transaction = fragmentManager.beginTransaction()
        //如果之前没有添加过
        if (!fragments[currentIndex].isAdded) {
            transaction
                    .hide(currentFragment)
                    .add(R.id.fragment_view, fragments[currentIndex], "" + currentIndex)  //第三个参数为添加当前的fragment时绑定一个tag
        } else {
            transaction
                    .hide(currentFragment)
                    .show(fragments[currentIndex])
        }
        currentFragment = fragments[currentIndex]
        transaction.commit()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun exit() {
        if (EXIT) {
            //true直接退出手机界面
            finish()
            moveTaskToBack(true)
            MyApplication.getInstance().remove()//移除所有activity
        } else {
            EXIT = true
            ToastUtil.showError(this, "再按一次退出")
            val timer = Timer()
            val task = object : TimerTask() {
                override fun run() {
                    EXIT = false
                    timer.cancel()
                }
            }
            //启动计时，1.5秒无退出操作，重置开关
            timer.schedule(task, 1500)
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }
}