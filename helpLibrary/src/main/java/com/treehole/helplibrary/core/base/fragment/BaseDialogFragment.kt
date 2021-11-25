package com.treehole.helplibrary.core.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.treehole.helplibrary.core.base.BaseViewModel
import com.treehole.helplibrary.ui.dialog.loading.LoadingDialogFragment
import com.treehole.helplibrary.utils.LoadingDialogHelp

/**
 * 带有 Dialog 的 Fragment 基类
 * @param BVM
 * @param DB : ViewDataBinding
 */
abstract class BaseDialogFragment<BVM : BaseViewModel, DB : ViewDataBinding> :
    AppCompatDialogFragment() {

    protected lateinit var dataBinding: DB
    protected val viewModel by lazy { initViewModel() }

    private val loadingDialog by lazy {
        LoadingDialogFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, fragmentLayoutResId(), container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //执行功能入口函数
        fragmentFunc()
        fragmentListener()
        fragmentObserver()

        viewModelObserver()
    }

    private fun viewModelObserver() {
        viewModel.loadingDialogState.observe(this) {
            if (it) {
                if (!LoadingDialogHelp.loadingDialog.isVisible)
                    LoadingDialogHelp.loadingDialog.show(childFragmentManager, "loading")
            } else {
                if (LoadingDialogHelp.loadingDialog.isVisible)
                    LoadingDialogHelp.loadingDialog.dismiss()
            }
        }
    }

    abstract fun initViewModel(): BVM

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

    /**
     * 参考
     * 设置一些 DialogFragment 属性
     */
//    override fun onStart() {
//        super.onStart()
//        dialog?.run {
//            builder?.let {
//                setCanceledOnTouchOutside(it.getCanceledOnTouchOutside())
//                setCancelable(it.getCancelable())
//                window?.run {
//                    setBackgroundDrawableResource(it.getBackgroundColor())
//                    if (it.getWidth() != 0F) {
//                        val lp = attributes
//                        lp.width = (ScreenUtils.getAppScreenWidth() * it.getWidth()).toInt()
//                        attributes = lp
//                    }
//                }
//            }
//        }
//    }

}