package com.treehole.helplibrary.ui.dialog.common

import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.blankj.utilcode.util.ScreenUtils
import com.treehole.helplibrary.R
import com.treehole.helplibrary.core.base.fragment.BaseDialogFragment
import com.treehole.helplibrary.core.extend.click
import com.treehole.helplibrary.core.extend.createViewModel
import com.treehole.helplibrary.databinding.FragmentPublicDialogBinding

/**
 * 带有两个按钮的统一提示 DialogFragment
 */
class PublicDialogFragment private constructor() :
    BaseDialogFragment<PublicDialogViewModel, FragmentPublicDialogBinding>() {

    private var builder: Builder? = null

    constructor(builder: Builder) : this() {
        this.builder = builder
    }

    override fun initViewModel(): PublicDialogViewModel {
        return createViewModel(this)
    }

    override fun fragmentLayoutResId(): Int {
        return R.layout.fragment_public_dialog
    }

    override fun fragmentFunc() {
        bindingAdapter()
    }

    private fun bindingAdapter() {
        builder?.let {
            dataBinding.run {
                it.setOnFunc1Click(click {
                    it.getOnFunctionListener()?.func1(this@PublicDialogFragment)
                })
                it.setOnFunc2Click(click {
                    it.getOnFunctionListener()?.func2(this@PublicDialogFragment)
                })
                builder = it
            }
        }
    }

    override fun fragmentListener() {

    }

    override fun fragmentObserver() {

    }

    override fun onStart() {
        super.onStart()
        dialog?.run {
            builder?.let {
                setCanceledOnTouchOutside(it.getCanceledOnTouchOutside())
                setCancelable(it.getCancelable())
                window?.run {
                    setBackgroundDrawableResource(it.getBackgroundColor())
                    if (it.getWidth() != 0F) {
                        val lp = attributes
                        lp.width = (ScreenUtils.getAppScreenWidth() * it.getWidth()).toInt()
                        attributes = lp
                    }
                }
            }
        }
    }

    class Builder {

        private var titleRes = R.string.publicDialogTitle
        private var titleColor = R.color.white
        private var contentRes = R.string.publicDialogContent
        private var contentColor = R.color.white
        private var remindRes = R.string.publicDialogRemind
        private var remindColor = R.color.white
        private var isShowRemind = false
        private var func1TextRes = R.string.publicDialogFunc1
        private var func1TextColor = R.color.white
        private var func2TextRes = R.string.publicDialogFunc2
        private var func2TextColor = R.color.white
        private var onFunctionListener: OnFunctionListener? = null
        private var width = 0F // 设置宽度系数 0 - 1
        private var canceledOnTouchOutside = true // 对话框点击外部可取消
        private var cancelable = true // 对话框点击 Back 可取消
        private var backgroundColor = android.R.color.transparent // Dialog 默认的背景颜色
        private var onFunc1Click: View.OnClickListener? = null
        private var onFunc2Click: View.OnClickListener? = null

        fun getOnFunc1Click() = onFunc1Click

        fun getOnFunc2Click() = onFunc2Click

        fun getBackgroundColor() = backgroundColor

        fun getWidth() = width

        fun getCanceledOnTouchOutside() = canceledOnTouchOutside

        fun getCancelable() = cancelable

        fun getTitleRes() = titleRes

        fun getTitleColor() = titleColor

        fun getContentRes() = contentRes

        fun getContentColor() = contentColor

        fun getRemindRes() = remindRes

        fun getRemindColor() = remindColor

        fun isShowRemind(): Boolean = isShowRemind

        fun getFunc1TextRes() = func1TextRes

        fun getFunc1TextColor() = func1TextColor

        fun getFunc2TextRes() = func2TextRes

        fun getFunc2TextColor() = func2TextColor

        fun setBackgroundColor(@ColorRes backgroundColor: Int): Builder {
            this.backgroundColor = backgroundColor
            return this
        }

        fun setCanceledOnTouchOutside(canceledOnTouchOutside: Boolean): Builder {
            this.canceledOnTouchOutside = canceledOnTouchOutside
            return this
        }

        fun setCancelable(cancelable: Boolean): Builder {
            this.cancelable = cancelable
            return this
        }

        fun setWidth(width: Float): Builder {
            this.width = width
            return this
        }

        fun setTitleRes(@StringRes titleRes: Int): Builder {
            this.titleRes = titleRes
            return this
        }

        fun setTitleColor(@ColorRes titleColor: Int): Builder {
            this.titleColor = titleColor
            return this
        }

        fun setContentRes(@StringRes contentRes: Int): Builder {
            this.contentRes = contentRes
            return this
        }

        fun setContentColor(@ColorRes contentColor: Int): Builder {
            this.contentColor = contentColor
            return this
        }

        fun setRemindRes(@StringRes remindRes: Int): Builder {
            this.remindRes = remindRes
            return this
        }

        fun setRemindColor(@ColorRes remindColor: Int): Builder {
            this.remindColor = remindColor
            return this
        }

        fun setShowRemind(show: Boolean): Builder {
            this.isShowRemind = show
            return this
        }

        fun setFunc1TextRes(@StringRes func1TextRes: Int): Builder {
            this.func1TextRes = func1TextRes
            return this
        }

        fun setFunc1TextColor(@ColorRes func1TextColor: Int): Builder {
            this.func1TextColor = func1TextColor
            return this
        }

        fun setFunc2TextRes(@StringRes func2TextRes: Int): Builder {
            this.func2TextRes = func2TextRes
            return this
        }

        fun setFunc2TextColor(@ColorRes func2TextColor: Int): Builder {
            this.func2TextColor = func2TextColor
            return this
        }

        fun setOnFunctionListener(onFunctionListener: OnFunctionListener): Builder {
            this.onFunctionListener = onFunctionListener
            return this
        }

        fun getOnFunctionListener(): OnFunctionListener? {
            return onFunctionListener
        }

        fun setOnFunc1Click(onClickListener: View.OnClickListener): Builder {
            this.onFunc1Click = onClickListener
            return this
        }

        fun setOnFunc2Click(onClickListener: View.OnClickListener): Builder {
            this.onFunc2Click = onClickListener
            return this
        }

        fun build(): PublicDialogFragment {
            return PublicDialogFragment(this)
        }
    }

    interface OnFunctionListener {
        fun func1(dialog: PublicDialogFragment)
        fun func2(dialog: PublicDialogFragment)
    }
}