package com.treehole.helplibrary.core.extend

import com.blankj.utilcode.util.ToastUtils

/**
 * Toast 扩展函数
 * @receiver String
 */
fun String.toast() {
    ToastUtils.showShort(this)
}