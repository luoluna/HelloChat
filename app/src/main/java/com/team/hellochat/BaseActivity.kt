package com.team.hellochat

import android.app.ActivityManager
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.team.hellochat.utils.LogUtil
import com.team.hellochat.utils.ToastUtil

/**
 * Created by Sweven on 2019/3/23.
 * Email:sweventears@Foxmail.com
 */
open class BaseActivity : AppCompatActivity(), View.OnClickListener {

    var log: LogUtil? = null

    private var isCurrentRunningForeground = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        log = LogUtil(application.packageName)
    }

    fun isRunningForeground(): Boolean {
        val activityManager = this.getSystemService(ACTIVITY_SERVICE) as ActivityManager
        val appProcessInfos = activityManager.runningAppProcesses
        // 枚举进程,查看该应用是否在运行
        for (appProcessInfo in appProcessInfos) {
            if (appProcessInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                if (appProcessInfo.processName == this.applicationInfo.processName) {
                    return true
                }
            }
        }
        return false
    }

    override fun onClick(v: View?) {}

    override fun onStop() {
        super.onStop()
        isCurrentRunningForeground = isRunningForeground()
        if (isCurrentRunningForeground) {

        }
    }

}