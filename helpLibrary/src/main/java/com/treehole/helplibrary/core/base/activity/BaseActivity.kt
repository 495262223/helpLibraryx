package com.treehole.helplibrary.core.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.BusUtils
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.LogUtils
import com.treehole.helplibrary.AppConfig
import com.treehole.helplibrary.core.bus.BusUtilsConfig

/**
 * 所有的 Activity 的基类
 */
abstract class BaseActivity : AppCompatActivity() {

    protected val TAG by lazy { javaClass.simpleName }

    private var isDataBinding = false

    override fun onCreate(savedInstanceState: Bundle?) {
        //设置 App 的主题 , 主题设置请在 AppConfig 中配置
        if (AppConfig.appTheme != 0) setTheme(AppConfig.appTheme)
        super.onCreate(savedInstanceState)
        // 判断子类是否有 DataBinding
        if (isDataBinding) {
            bindDataBinding()
        } else {
            setContentView(activityLayoutResId())
        }
        // 是否开启 BusUtils , 如果开启 , 则进行绑定
        if (BusUtilsConfig.isOpenBusUtils) BusUtils.register(this) else LogUtils.e(
            TAG,
            "提示:",
            "未打开BusUtils,不进行BusUtils注册.如需使用BusUtils,请查看 <xxx.helplibrary.core.bus.BusUtilsConfig> 配置"
        )

        settingAppBar()

        //执行功能入口函数
        activityFunc()
        activityListener()
        activityObserver()

        viewModelObserver()
    }

    open fun bindDataBinding() {}

    open fun viewModelObserver() {}

    fun setDataBinding(isDataBinding: Boolean) {
        this.isDataBinding = isDataBinding
    }

    /**
     * 设置任务栏颜色
     * @param colorAppBar Int
     */
    fun settingAppBar(
        colorAppBar: Int = if (AppConfig.appThemeBarColor == 0) android.R.color.transparent else AppConfig.appThemeBarColor,
    ) {
        BarUtils.setStatusBarColor(this, ColorUtils.getColor(colorAppBar))
    }

    /**
     * 页面布局 Id
     * @return Int
     */
    abstract fun activityLayoutResId(): Int

    /**
     * 功能入口
     */
    abstract fun activityFunc()

    /**
     * 监听事件
     */
    abstract fun activityListener()

    /**
     * 观察者
     */
    abstract fun activityObserver()

    override fun onDestroy() {
        super.onDestroy()
        // 使用完 Bus 后 , 重写 onDestroy() 清除使用的 BusTag
        // 解除 BusUtils 绑定
        if (BusUtilsConfig.isOpenBusUtils) BusUtils.unregister(this)
    }

    /**
     * 使用示例
     * 在使用的 Activity 重写
     *
     *     override fun onDestroy() {
     *         BusUtils.removeSticky(tag)
     *         super.onDestroy()
     *     }
     *
     */

}