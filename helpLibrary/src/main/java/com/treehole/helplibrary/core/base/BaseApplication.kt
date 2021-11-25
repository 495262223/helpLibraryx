package com.treehole.helplibrary.core.base

import android.app.Application
import android.content.Context
import android.view.Gravity
import androidx.multidex.MultiDex
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.ToastUtils
import com.treehole.helplibrary.R

open class BaseApplication : Application() {

    companion object {
        lateinit var mApplicationContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        // 获取一个 全局上下文
        mApplicationContext = this

        // 解决 65533 问题
        MultiDex.install(this)

        // toast 初始设置
        toastUtilsSetting()
    }

    /**
     * 初始设置 Toast 样式
     */
    private fun toastUtilsSetting() {
        ToastUtils.getDefaultMaker().apply {
            setBgColor(ColorUtils.getColor(R.color.black))
            setGravity(Gravity.CENTER, 0, 0)
            setTextColor(ColorUtils.getColor(R.color.white))
        }
    }

}