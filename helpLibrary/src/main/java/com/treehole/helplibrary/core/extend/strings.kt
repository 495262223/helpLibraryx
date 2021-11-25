package com.treehole.helplibrary.core.extend

import com.blankj.utilcode.util.StringUtils

fun Int.string(): String {
    return StringUtils.getString(this)
}