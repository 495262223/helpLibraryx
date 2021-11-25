package com.treehole.helplibrary.core.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.BusUtils
import com.treehole.helplibrary.core.bus.BusUtilsConfig

/**
 * 所有的 Fragment 的基类
 */
abstract class BaseFragment : Fragment() {

    protected val TAG by lazy { javaClass.simpleName }

    private var isDataBinding = false

    private lateinit var mView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!isDataBinding) {
            mView = inflater.inflate(fragmentLayoutResId(), container, false)
        }
        return mView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // 是否开启 BusUtils , 如果开启 , 则进行绑定
        if (BusUtilsConfig.isOpenBusUtils) BusUtils.register(this)

        //执行功能入口函数
        fragmentFunc()
        fragmentListener()
        fragmentObserver()

        viewModelObserver()
    }

    fun setContentView(view: View) {
        this.mView = view
    }

    fun setDataBinding(isDataBinding: Boolean) {
        this.isDataBinding = isDataBinding
    }

    open fun viewModelObserver() {}

    /**
     * 懒加载 , 只执行一次
     */
    abstract fun fragmentLazyFunc()

    /**
     * 页面布局 Id
     * @return Int
     */
    abstract fun fragmentLayoutResId(): Int

    /**
     * 功能入口
     */
    abstract fun fragmentFunc()

    /**
     * 监听事件
     */
    abstract fun fragmentListener()

    /**
     * 观察者
     */
    abstract fun fragmentObserver()

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