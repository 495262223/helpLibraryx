package com.treehole.helplibrary.core.extend

import android.view.View
import com.blankj.utilcode.util.ClickUtils

/**
 * 简化 View 的点击事件 , 添加防抖动功能
 * @receiver View
 * @param duration Long 防抖动事件
 * @param block Function0<Unit> 事件
 */
inline fun View.click(duration: Long = 1000L, crossinline block: () -> Unit) {
    ClickUtils.applyGlobalDebouncing(this, duration) {
        block()
    }
}

inline fun click(duration: Long = 1000L, crossinline block: View.() -> Unit): View.OnClickListener {
    return object : ClickUtils.OnDebouncingClickListener(duration) {
        override fun onDebouncingClick(v: View?) {
            v?.block()
        }
    }
}

inline fun clicks(vararg view: View, duration: Long = 1000L, crossinline block: View.() -> Unit) {
    val listener = View.OnClickListener { it.block() }
    view.forEach { ClickUtils.applyGlobalDebouncing(it, duration, listener) }
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}